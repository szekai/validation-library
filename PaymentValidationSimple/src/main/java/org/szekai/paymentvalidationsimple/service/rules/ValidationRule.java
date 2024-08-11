package org.szekai.paymentvalidationsimple.service.rules;

import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;

public interface ValidationRule {
    void validate(Transaction transaction) throws ValidationException;
}

