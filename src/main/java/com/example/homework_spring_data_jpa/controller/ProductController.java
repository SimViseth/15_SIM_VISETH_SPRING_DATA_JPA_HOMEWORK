package com.example.homework_spring_data_jpa.controller;

import com.example.homework_spring_data_jpa.dto.ApiDeleteResponse;
import com.example.homework_spring_data_jpa.dto.ApiResponse;
import com.example.homework_spring_data_jpa.dto.request.ProductRequest;
import com.example.homework_spring_data_jpa.dto.response.OrderResponse;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import com.example.homework_spring_data_jpa.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> addProduct(@RequestBody List<ProductRequest> productRequest) {
        List<ProductResponse> response = productService.addProduct(productRequest);
        ApiResponse<List<ProductResponse>> apiResponse = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.CREATED)
                .message("A new product is created successfully.")
                .payload(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "productId") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction orderBy
            )
    {
        List<ProductResponse> response = productService.getAllProduct(orderBy, sortBy, page, size);
        ApiResponse<List<ProductResponse>> apiResponse = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.OK)
                .message("get all products successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("id") Integer id) {
        ProductResponse response = productService.getProductById(id);
        ApiResponse<ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("get products by id successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProduct(id, productRequest);
        ApiResponse<ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("update product successfully.")
                .payload(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDeleteResponse<String>> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        ApiDeleteResponse<String> apiResponse = ApiDeleteResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Product with id " + id + " is deleted successfully.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
