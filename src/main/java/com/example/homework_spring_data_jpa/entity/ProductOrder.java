package com.example.homework_spring_data_jpa.entity;

import com.example.homework_spring_data_jpa.dto.response.ProductOrderResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

    public ProductOrderResponse toResponse() {
        return new ProductOrderResponse(this.id, this.product.getProductName(), this.product.getUnitPrice(), this.product.getDescription(), this.quantity);
    }
}
