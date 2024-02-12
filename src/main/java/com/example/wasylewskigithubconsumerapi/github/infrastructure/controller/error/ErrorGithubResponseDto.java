package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorGithubResponseDto(HttpStatus status, String message) {
}
