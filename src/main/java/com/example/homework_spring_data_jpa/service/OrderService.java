package com.example.homework_spring_data_jpa.service;

import com.example.homework_spring_data_jpa.dto.request.OrderRequest;
import com.example.homework_spring_data_jpa.dto.request.ProductOrderRequest;
import com.example.homework_spring_data_jpa.dto.response.OrderResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductOrderResponse;
import com.example.homework_spring_data_jpa.entity.Order;

import java.util.List;
public interface OrderService {
    OrderResponse orderProduct(Integer customerId, List<OrderRequest> orderRequest);

    List<OrderResponse> getOrderByCustomerId(Integer customerId);

    OrderResponse updateOrderStatus(Order.Status status, Integer orderId);

    OrderResponse getOrderByOrderId(Integer orderId);
}
