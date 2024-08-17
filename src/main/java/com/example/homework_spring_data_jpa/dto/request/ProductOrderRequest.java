package com.example.homework_spring_data_jpa.dto.request;

import com.example.homework_spring_data_jpa.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequest {
    private Integer productId;
    private Integer quantity;
}
