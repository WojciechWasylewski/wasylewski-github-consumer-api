package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.error;

public class InvalidFormatResponseError extends RuntimeException {
    public InvalidFormatResponseError(String message) {
        super(message);
    }
}
