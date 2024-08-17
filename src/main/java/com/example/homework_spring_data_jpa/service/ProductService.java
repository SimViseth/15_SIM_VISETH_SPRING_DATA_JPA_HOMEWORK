package com.example.homework_spring_data_jpa.service;

import com.example.homework_spring_data_jpa.dto.request.ProductRequest;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    List<ProductResponse> addProduct(List<ProductRequest> productRequest);

    List<ProductResponse> getAllProduct(Sort.Direction orderBy, String sortBy, Integer page, Integer size);

    ProductResponse getProductById(Integer id);

    void deleteProduct(Integer id);

    ProductResponse updateProduct(Integer id, ProductRequest productRequest);

}
