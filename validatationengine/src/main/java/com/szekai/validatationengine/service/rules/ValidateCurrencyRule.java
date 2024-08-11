package com.szekai.validatationengine.service.rules;

import com.szekai.validatationengine.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidateCurrencyRule implements ValidationRule {

    @Override
    public void validate(Transaction transaction) throws ValidationException {
        if (transaction.getCurrency() == null) {
            throw new ValidationException("Currency is invalid");
        }
    }
}
