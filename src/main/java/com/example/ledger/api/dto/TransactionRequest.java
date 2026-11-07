package com.example.ledger.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TransactionRequest(
        @NotBlank String description,
        @NotNull @Valid List<EntryRequest> entriesList
) {}