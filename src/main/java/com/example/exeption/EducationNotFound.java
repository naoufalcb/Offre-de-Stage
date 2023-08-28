package com.example.exeption;

public class EducationNotFound extends RuntimeException {
    public EducationNotFound(String message) {
        super(message);
    }
}
