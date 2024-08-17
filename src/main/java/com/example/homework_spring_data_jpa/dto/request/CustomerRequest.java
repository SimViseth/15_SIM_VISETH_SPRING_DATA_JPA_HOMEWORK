package com.example.homework_spring_data_jpa.dto.request;

import com.example.homework_spring_data_jpa.dto.response.CustomerResponse;
import com.example.homework_spring_data_jpa.entity.Customer;
import com.example.homework_spring_data_jpa.entity.Email;
import com.example.homework_spring_data_jpa.entity.Product;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer toEntity() {
        Email email1 = new Email(null, this.email,null);
        return new Customer(null, this.customerName, this.address, this.phoneNumber, email1);
    }

    public Customer toEntity(Integer id) {
        Email email2 = new Email(id, this.email, null);
        return new Customer(id, this.customerName, this.address, this.phoneNumber, email2, null);
    }
}
