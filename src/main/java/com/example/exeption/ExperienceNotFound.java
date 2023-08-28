package com.example.exeption;

public class ExperienceNotFound extends RuntimeException {
    public ExperienceNotFound(String message) {
        super(message);
    }
}
