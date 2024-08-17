package com.example.homework_spring_data_jpa.controller;


import com.example.homework_spring_data_jpa.dto.ApiDeleteResponse;
import com.example.homework_spring_data_jpa.dto.ApiResponse;
import com.example.homework_spring_data_jpa.dto.request.CustomerRequest;
import com.example.homework_spring_data_jpa.dto.request.ProductRequest;
import com.example.homework_spring_data_jpa.dto.response.CustomerResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import com.example.homework_spring_data_jpa.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.addCustomer(customerRequest);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED)
                .message("Customer created successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @RequestParam(required = false, defaultValue = "customerName") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction orderBy
    )
    {
        List<CustomerResponse> response = customerService.getAllCustomer(orderBy, sortBy, page, size);
        ApiResponse<List<CustomerResponse>> apiResponse = ApiResponse.<List<CustomerResponse>>builder()
                .status(HttpStatus.OK)
                .message("get all customers successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerResponse response = customerService.getCustomerById(id);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("get customer by id successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Integer id,
            @RequestBody CustomerRequest customerRequest) {

        CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerRequest);

        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("Customer updated successfully.")
                .payload(updatedCustomer)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDeleteResponse<String>> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        ApiDeleteResponse<String> apiResponse = ApiDeleteResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Customer with id " + id + " is deleted successfully.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
