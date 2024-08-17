package com.example.homework_spring_data_jpa.service;

import com.example.homework_spring_data_jpa.dto.request.CustomerRequest;
import com.example.homework_spring_data_jpa.dto.response.CustomerResponse;
import com.example.homework_spring_data_jpa.entity.Email;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomer(Sort.Direction orderBy, String sortBy, Integer page, Integer size);

    CustomerResponse getCustomerById(Integer id);

    CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest);

    void deleteCustomer(Integer id);

}
