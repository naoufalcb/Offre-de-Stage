package com.example.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class ApiError {

    private String message;
    private LocalDateTime timeStamp;
}
