package com.example.ledger.api.controller;

import com.example.ledger.api.dto.OpenAccountRequest;
import com.example.ledger.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/open")
    public void open(@NotNull @Valid @RequestBody OpenAccountRequest openAccountRequest) {
        accountService.openAccount(openAccountRequest);
    }

}
