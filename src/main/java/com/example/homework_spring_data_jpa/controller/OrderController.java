package com.example.homework_spring_data_jpa.controller;

import com.example.homework_spring_data_jpa.dto.ApiResponse;
import com.example.homework_spring_data_jpa.dto.request.OrderRequest;
import com.example.homework_spring_data_jpa.dto.response.OrderResponse;
import com.example.homework_spring_data_jpa.entity.Order;
import com.example.homework_spring_data_jpa.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{customerId}")
    public ResponseEntity<ApiResponse<OrderResponse>> orderProduct(@PathVariable Integer customerId, @RequestBody List<OrderRequest> orderRequests) {
        OrderResponse response = orderService.orderProduct(customerId, orderRequests);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new order is created successfully.")
                .payload(response)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrderByCustomerId(@PathVariable Integer customerId) {
        List<OrderResponse> response = orderService.getOrderByCustomerId(customerId);
        ApiResponse<List<OrderResponse>> apiResponse = ApiResponse.<List<OrderResponse>>builder()
                .status(HttpStatus.OK)
                .message("get order by customer id successfully.")
                .payload(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderStatus(
            @RequestParam("status") Order.Status status,
            @RequestParam Integer orderId) {

        OrderResponse updatedOrder = orderService.updateOrderStatus(status, orderId);

        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("Successfully updated the status of order to " + status + ".")
                .payload(updatedOrder)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderByOrderId(@PathVariable Integer orderId) {
        OrderResponse response = orderService.getOrderByOrderId(orderId);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("get order by order id successfully.")
                .payload(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
