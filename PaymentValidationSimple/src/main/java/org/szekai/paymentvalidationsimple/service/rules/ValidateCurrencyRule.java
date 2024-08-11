package org.szekai.paymentvalidationsimple.service.rules;

import org.springframework.stereotype.Component;
import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;

@Component
public class ValidateCurrencyRule implements ValidationRule {

    @Override
    public void validate(Transaction transaction) throws ValidationException {
        // Implement the validation logic for Currency
        if (transaction.getCurrency() == null) {
            throw new ValidationException("Currency is invalid");
        }
    }
}
