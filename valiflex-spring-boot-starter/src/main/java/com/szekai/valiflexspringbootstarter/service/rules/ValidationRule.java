package com.szekai.valiflexspringbootstarter.service.rules;

import com.szekai.valiflexspringbootstarter.exception.ValidationException;
import com.szekai.valiflexspringbootstarter.vo.Transaction;

public interface ValidationRule {
    void validate(Transaction transaction) throws ValidationException;
}

