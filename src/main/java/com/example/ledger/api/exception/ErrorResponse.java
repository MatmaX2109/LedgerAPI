package com.example.ledger.api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> validations, // Va conține câmpurile și mesajele de eroare (ex: "amount": "Suma trebuie să fie strict pozitivă.")
        LocalDateTime timestamp
) {
    public ErrorResponse(int status, String error, String message) {
        this(status, error, message, null, LocalDateTime.now());
    }

    public ErrorResponse(int status, String error, String message, Map<String, String> validations) {
        this(status, error, message, validations, LocalDateTime.now());
    }
}

