package org.szekai.paymentvalidationsimple.rules;

import com.szekai.valiflexspringbootstarter.exception.ValidationException;
import com.szekai.valiflexspringbootstarter.service.rules.ValidationRule;
import com.szekai.valiflexspringbootstarter.vo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckTTTypeRule implements ValidationRule {


    @Override
    public void validate(Transaction transaction) throws ValidationException {
        // Implement the validation logic for TT Type
        if (transaction.getTtType() == null) {
            throw new ValidationException("TT Type is invalid");
        }
    }
}



