package com.example.ledger.api.dto;

import com.example.ledger.enums.CurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OpenAccountRequest(
        @NotBlank String name,
        @NotNull CurrencyCode currencyCode,
        @NotNull BigDecimal overdraftLimit
        ) {}
