package com.example.homework_spring_data_jpa.entity;

import com.example.homework_spring_data_jpa.dto.response.CustomerResponse;
import com.example.homework_spring_data_jpa.dto.response.EmailResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    private String address;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    private Email email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderList;

    public Customer(Integer customerId, String customerName, String address, String phoneNumber, Email email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerResponse toResponse() {
        return new CustomerResponse(this.customerId, this.customerName, this.address, this.phoneNumber, email);
    }
}
