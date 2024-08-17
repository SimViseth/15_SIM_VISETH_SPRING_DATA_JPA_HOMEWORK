package com.example.homework_spring_data_jpa.entity;

import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    private double unitPrice;

    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders;

    public ProductResponse toResponse() {
        return new ProductResponse(this.productId, this.productName, this.unitPrice, this.description);
    }
}
