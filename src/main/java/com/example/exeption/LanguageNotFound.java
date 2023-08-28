package com.example.exeption;

public class LanguageNotFound extends RuntimeException {
    public LanguageNotFound(String message) {
        super(message);
    }
}
