package com.example.homework_spring_data_jpa.repository;

import com.example.homework_spring_data_jpa.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
}
