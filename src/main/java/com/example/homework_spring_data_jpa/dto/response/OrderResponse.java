package com.example.homework_spring_data_jpa.dto.response;

import com.example.homework_spring_data_jpa.entity.Order;
import com.example.homework_spring_data_jpa.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private LocalDate orderDate;
    private float totalAmount;
    private Order.Status status;
    private List<ProductOrderResponse> productList;
}
