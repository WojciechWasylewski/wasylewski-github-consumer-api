package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.error;

public class GithubUserNotFoundException extends RuntimeException {
    public GithubUserNotFoundException(String message) {
        super(message);
    }

}
