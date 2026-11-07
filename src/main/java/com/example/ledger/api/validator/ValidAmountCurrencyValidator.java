package com.example.ledger.api.validator;

import com.example.ledger.api.dto.EntryRequest;
import com.example.ledger.enums.CurrencyCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidAmountCurrencyValidator implements ConstraintValidator<ValidAmountCurrency, EntryRequest> {

    @Override
    public boolean isValid(EntryRequest entry, ConstraintValidatorContext context) {
        // Dacă obiectul sau valorile esențiale sunt null, lăsăm @NotNull-urile de pe câmpuri să își facă treaba
        if (entry == null || entry.amount() == null || entry.account().currencyCode() == null) {
            return true;
        }

        BigDecimal amount = entry.amount();
        CurrencyCode currency = entry.account().currencyCode();

        // 1. Verificare: Suma trebuie să fie strict pozitivă (> 0)
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Suma trebuie să fie strict pozitivă.")
                    .addPropertyNode("amount")
                    .addConstraintViolation();
            return false;
        }

        // 2. Verificare: Numărul de zecimale conform ISO 4217 din Enum
        int maxDecimals = currency.getDecimalPlaces();
        if (amount.scale() > maxDecimals) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Valuta " + currency.name() + " permite maximum " + maxDecimals + " zecimale."
                    ).addPropertyNode("amount")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}