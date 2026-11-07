package com.example.ledger.repository;

import com.example.ledger.domain.entity.Account;
import com.example.ledger.domain.entity.AccountId;
import com.example.ledger.enums.CurrencyCode;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, AccountId> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findAndLockByNameAndCurrencyCode(String name, CurrencyCode currencyCode);
}
