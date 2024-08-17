package com.example.homework_spring_data_jpa.repository;

import com.example.homework_spring_data_jpa.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
