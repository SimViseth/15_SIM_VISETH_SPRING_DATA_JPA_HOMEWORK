package com.example.homework_spring_data_jpa.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Integer productId;

    private String productName;

    private double unitPrice;

    private String description;
}
