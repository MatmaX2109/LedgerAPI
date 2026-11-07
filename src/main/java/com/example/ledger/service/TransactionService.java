package com.example.ledger.service;

import com.example.ledger.api.dto.EntryRequest;
import com.example.ledger.api.dto.TransactionRequest;
import com.example.ledger.domain.entity.Account;
import com.example.ledger.domain.entity.AccountId;
import com.example.ledger.domain.entity.Transaction;
import com.example.ledger.enums.Direction;
import com.example.ledger.repository.AccountRepository;
import com.example.ledger.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Thread.sleep;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public UUID recordTransaction(final TransactionRequest transactionRequest) throws InterruptedException {
        for (EntryRequest entryRequest : transactionRequest.entriesList()) {
            var accountId = new AccountId(entryRequest.account().name(), entryRequest.account().currencyCode());
            Account account = accountRepository.findAndLockByNameAndCurrencyCode(accountId.getName(), accountId.getCurrencyCode())
                    .orElseThrow(() -> new IllegalArgumentException("Account not found: " + entryRequest.account().name()));

            if (entryRequest.direction() == Direction.DEBIT) {
                account.setBalance(account.getBalance().subtract(entryRequest.amount()));

                var overdraftLimit = BigDecimal.valueOf(0).subtract(account.getOverdraftLimit());
                if (account.getBalance().compareTo(overdraftLimit) < 0) {
                    throw new IllegalStateException("The overdraft limit is exceeded for account: " + account.getName());
                }
            } else {
                account.setBalance(account.getBalance().add(entryRequest.amount()));
            }

            accountRepository.save(account);
        }

        Transaction transaction = Transaction.builder()
                .requestPayload(transactionRequest)
                .build();
        return transactionRepository.save(transaction).getUuid();
    }

    @Transactional
    public UUID reverseTransaction(final UUID uuid) {
        Optional<Transaction> transaction = transactionRepository.findById(uuid);

        if (transaction.isPresent()){
            if (transaction.get().isReverted()) {
                throw new IllegalStateException("Transaction was already reverted");
            }
            for (EntryRequest entryRequest : transaction.get().getRequestPayload().entriesList()) {
                var accountId = new AccountId(entryRequest.account().name(), entryRequest.account().currencyCode());
                Account account = accountRepository.findAndLockByNameAndCurrencyCode(accountId.getName(), accountId.getCurrencyCode())
                        .orElseThrow(() -> new IllegalArgumentException("Account not found: " + entryRequest.account().name()));

                if (entryRequest.direction() == Direction.DEBIT) {
                    account.setBalance(account.getBalance().add(entryRequest.amount()));

                } else {
                    account.setBalance(account.getBalance().subtract(entryRequest.amount()));
                    var overdraftLimit = BigDecimal.valueOf(0).subtract(account.getOverdraftLimit());
                    if (account.getBalance().compareTo(overdraftLimit) < 0) {
                        throw new IllegalStateException("The overdraft limit is exceeded for account: " + account.getName());
                    }
                }

                accountRepository.save(account);
            }
            transaction.get().setReverted(true);

            return transactionRepository.save(transaction.get()).getUuid();

        }
        throw new IllegalStateException("Transaction not exist for uuid " + uuid);
    }
}
