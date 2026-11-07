package com.example.ledger.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    EUR(2),
    USD(2),
    JPY(0);

    private final int decimalPlaces;

    CurrencyCode(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

}

