package com.example.homework_spring_data_jpa.repository;

import com.example.homework_spring_data_jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
