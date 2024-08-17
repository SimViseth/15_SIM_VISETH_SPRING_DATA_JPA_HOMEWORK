package com.example.homework_spring_data_jpa.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiDeleteResponse<T> {
    private HttpStatus status;
    private String message;
}
