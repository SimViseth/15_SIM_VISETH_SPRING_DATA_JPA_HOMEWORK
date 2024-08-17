package com.example.homework_spring_data_jpa.dto.response;

import com.example.homework_spring_data_jpa.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerResponse {
    private Integer customer_id;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Email email;
}
