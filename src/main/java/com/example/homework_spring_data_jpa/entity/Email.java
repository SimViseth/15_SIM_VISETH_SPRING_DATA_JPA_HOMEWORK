package com.example.homework_spring_data_jpa.entity;

import com.example.homework_spring_data_jpa.dto.response.EmailResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "email", cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;
}
