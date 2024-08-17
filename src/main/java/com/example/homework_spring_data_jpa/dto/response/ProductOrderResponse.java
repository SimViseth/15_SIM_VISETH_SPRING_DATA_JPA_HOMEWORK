package com.example.homework_spring_data_jpa.dto.response;

import com.example.homework_spring_data_jpa.entity.Order;
import com.example.homework_spring_data_jpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderResponse {
    private Integer id;
    private String productName;
    private double unitPrice;
    private String description;
    private Integer quantity;
}
