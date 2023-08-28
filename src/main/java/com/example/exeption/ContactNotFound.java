package com.example.exeption;

public class ContactNotFound extends RuntimeException {
    public ContactNotFound(String message) {
        super(message);
    }
}
