package com.example.exeption.basic;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
}

