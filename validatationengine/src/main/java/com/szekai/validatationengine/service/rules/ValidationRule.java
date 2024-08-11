package com.szekai.validatationengine.service.rules;

import com.szekai.validatationengine.exception.ValidationException;

public interface ValidationRule {
    void validate(Transaction transaction) throws ValidationException;
}
