package com.example.homework_spring_data_jpa.service.serviceImpl;

import com.example.homework_spring_data_jpa.dto.request.ProductRequest;
import com.example.homework_spring_data_jpa.dto.response.ProductResponse;
import com.example.homework_spring_data_jpa.entity.Product;
import com.example.homework_spring_data_jpa.repository.ProductRepository;
import com.example.homework_spring_data_jpa.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<ProductResponse> addProduct(List<ProductRequest> productRequest) {
        List<Product> products = productRequest.stream()
                .map(ProductRequest::toEntity)
                .collect(Collectors.toList());

        List<Product> savedProducts = productRepository.saveAll(products);

        return savedProducts.stream()
                .map(Product::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProduct(Sort.Direction orderBy, String sortBy, Integer page, Integer size) {
        Sort sort = Sort.by(orderBy, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAll(pageable);

        return products.getContent().stream().map(Product::toResponse).toList();
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity(id)).toResponse();
    }

}
