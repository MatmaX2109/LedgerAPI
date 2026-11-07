package com.example.ledger.api.controller;

import com.example.ledger.api.dto.TransactionRequest;
import com.example.ledger.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/transaction")
@RequiredArgsConstructor
@Validated
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/record")
    public UUID transaction(@NotNull @Valid @RequestBody TransactionRequest transactionRequest) throws InterruptedException {
        return transactionService.recordTransaction(transactionRequest);
    }

    @PutMapping("/reverse")
    public UUID transaction(@NotNull @RequestParam("uuid") UUID uuid) {
        return transactionService.reverseTransaction(uuid);
    }
}
