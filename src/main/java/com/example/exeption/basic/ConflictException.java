package com.example.exeption.basic;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}

