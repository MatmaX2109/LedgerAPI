package com.example.ledger.service;


import com.example.ledger.api.dto.OpenAccountRequest;
import com.example.ledger.domain.entity.Account;
import com.example.ledger.domain.entity.AccountId;
import com.example.ledger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void openAccount(final OpenAccountRequest openAccountRequest){
        Account account = Account.builder()
                .name(openAccountRequest.name())
                .currencyCode(openAccountRequest.currencyCode())
                .overdraftLimit(openAccountRequest.overdraftLimit())
                .balance(BigDecimal.valueOf(0))
                .build();

        var accountId = new AccountId(openAccountRequest.name(), openAccountRequest.currencyCode());
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            throw new IllegalStateException("The account with this name and this currency already exists!");
        } else {
            accountRepository.save(account);
        }

    }
}
