package com.example.homework_spring_data_jpa.entity;


import com.example.homework_spring_data_jpa.dto.response.OrderResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductOrderResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate orderDate;

    private float totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrder;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OrderResponse toResponse() {
        List<ProductOrderResponse> productOrderResponses = this.productOrder.stream()
                .map(ProductOrder::toResponse)
                .collect(Collectors.toList());

        return new OrderResponse(this.orderId, this.orderDate, this.totalAmount, this.status, productOrderResponses);
    }

    public enum Status {
        PENDING,
        SHIPPED,
        DELIVERING,
        DELIVERED
    }
}
