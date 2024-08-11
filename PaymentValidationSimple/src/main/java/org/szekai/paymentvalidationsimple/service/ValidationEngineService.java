package org.szekai.paymentvalidationsimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;
import org.szekai.paymentvalidationsimple.service.rules.ValidationRule;

import java.util.List;

@Service
public class ValidationEngineService {

    private final List<ValidationRule> validationRules;

    @Autowired
    public ValidationEngineService(List<ValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validateTransaction(Transaction transaction) throws ValidationException {
        for (ValidationRule rule : validationRules) {
            rule.validate(transaction);
        }
    }
}
