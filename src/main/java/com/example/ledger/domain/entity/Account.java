package com.example.ledger.domain.entity;

import com.example.ledger.enums.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@IdClass(AccountId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "currencyCode", nullable = false, updatable = false)
    private CurrencyCode currencyCode;

    @Column(name = "overdraftLimit", nullable = false, updatable = false)
    private BigDecimal overdraftLimit;

    @Column(name = "balance")
    private BigDecimal balance;
}
