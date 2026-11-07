package com.example.ledger.api.dto;

import com.example.ledger.enums.CurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
        @NotBlank String name,
        @NotNull CurrencyCode currencyCode
        ) {}
