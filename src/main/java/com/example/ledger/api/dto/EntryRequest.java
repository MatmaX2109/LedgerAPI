package com.example.ledger.api.dto;

import com.example.ledger.api.validator.ValidAmountCurrency;
import com.example.ledger.enums.Direction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@ValidAmountCurrency
public record EntryRequest(
        @NotBlank @Valid AccountRequest account,
        @NotNull Direction direction,
        @NotNull BigDecimal amount
) {}
