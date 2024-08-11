package org.szekai.paymentvalidationsimple.service.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;
import org.szekai.paymentvalidationsimple.repository.TransactionRepository;

@Component
public class CheckTTTypeRule implements ValidationRule {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void validate(Transaction transaction) throws ValidationException {
        // Implement the validation logic for TT Type
        if (transaction.getTtType() == null) {
            throw new ValidationException("TT Type is invalid");
        }
    }
}



