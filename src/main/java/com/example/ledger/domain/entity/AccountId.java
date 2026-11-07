package com.example.ledger.domain.entity;

import com.example.ledger.enums.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountId implements Serializable {
    private String name;
    private CurrencyCode currencyCode;
}
