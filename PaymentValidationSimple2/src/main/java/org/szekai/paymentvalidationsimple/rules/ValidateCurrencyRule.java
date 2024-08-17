package org.szekai.paymentvalidationsimple.rules;

import com.szekai.valiflexspringbootstarter.exception.ValidationException;
import com.szekai.valiflexspringbootstarter.service.rules.ValidationRule;
import com.szekai.valiflexspringbootstarter.vo.Transaction;
import org.springframework.stereotype.Component;


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
