package com.example.ledger.api.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAmountCurrencyValidator.class)
@Documented
public @interface ValidAmountCurrency {

    String message() default "The amount does not comply with the currency rules.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
