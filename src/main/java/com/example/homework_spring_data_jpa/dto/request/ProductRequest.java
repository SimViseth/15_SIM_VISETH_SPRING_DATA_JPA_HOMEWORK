package com.example.homework_spring_data_jpa.dto.request;

import com.example.homework_spring_data_jpa.entity.Product;
import com.example.homework_spring_data_jpa.entity.ProductOrder;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String productName;

    private double unitPrice;

    private String description;

    public Product toEntity() {
        return new Product(null, this.productName, this.unitPrice, this.description, null);
    }
    public Product toEntity(Integer id) {
        return new Product(id, this.productName, this.unitPrice, this.description, null);
    }
}
