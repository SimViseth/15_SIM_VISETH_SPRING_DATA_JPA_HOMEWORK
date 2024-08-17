package com.example.homework_spring_data_jpa.service.serviceImpl;

import com.example.homework_spring_data_jpa.dto.request.OrderRequest;
import com.example.homework_spring_data_jpa.dto.response.OrderResponse;
import com.example.homework_spring_data_jpa.entity.Customer;
import com.example.homework_spring_data_jpa.entity.Order;
import com.example.homework_spring_data_jpa.entity.Product;
import com.example.homework_spring_data_jpa.entity.ProductOrder;
import com.example.homework_spring_data_jpa.repository.CustomerRepository;
import com.example.homework_spring_data_jpa.repository.OrderRepository;
import com.example.homework_spring_data_jpa.repository.ProductRepository;
import com.example.homework_spring_data_jpa.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    @Override
    public OrderResponse orderProduct(Integer customerId, List<OrderRequest> orderRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        List<ProductOrder> productOrders = orderRequest.stream()
                .map(request -> {
                    Product product = productRepository.findById(request.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
                    return new ProductOrder(null, product, null, request.getQuantity());
                }).collect(Collectors.toList());

        float totalAmount = (float) productOrders.stream()
                .mapToDouble(po -> po.getProduct().getUnitPrice() * po.getQuantity())
                .sum();

        Order order = new Order(null, LocalDate.now(), totalAmount, Order.Status.PENDING, productOrders, customer);
        productOrders.forEach(po -> po.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        return savedOrder.toResponse();
    }

    @Override
    public List<OrderResponse> getOrderByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        List<Order> order = customer.getOrderList();

        return order.stream()
                .map(Order::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateOrderStatus(Order.Status status, Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        return updatedOrder.toResponse();
    }

    @Override
    public OrderResponse getOrderByOrderId(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow().toResponse();
    }
}
