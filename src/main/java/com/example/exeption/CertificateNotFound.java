package com.example.exeption;

public class CertificateNotFound extends RuntimeException {
    public CertificateNotFound(String message) {
        super(message);
    }
}
