package com.szekai.validatationengine.service.rules;

import org.springframework.stereotype.Component;

@Component
public class CheckTTTypeRule implements ValidationRule {

    @Override
    public void validate(Transaction transaction) throws ValidationException {
        if (transaction.getTtType() == null) {
            throw new ValidationException("TT Type is invalid");
        }
    }
}
