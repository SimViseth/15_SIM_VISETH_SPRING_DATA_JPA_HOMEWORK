package com.example.homework_spring_data_jpa.repository;

import com.example.homework_spring_data_jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
