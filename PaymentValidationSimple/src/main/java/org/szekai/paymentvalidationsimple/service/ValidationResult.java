package org.szekai.paymentvalidationsimple.service;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private boolean valid = true;
    private final List<String> passedRules = new ArrayList<>();
    private final List<String> failedRules = new ArrayList<>();
    private final List<String> errorMessages = new ArrayList<>();

    public boolean isValid() {
        return valid;
    }

    public void addPassedRule(String ruleName) {
        passedRules.add(ruleName);
    }

    public void addFailedRule(String ruleName, String errorMessage) {
        valid = false;
        failedRules.add(ruleName);
        errorMessages.add(errorMessage);
    }

    public List<String> getPassedRules() {
        return passedRules;
    }

    public List<String> getFailedRules() {
        return failedRules;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
