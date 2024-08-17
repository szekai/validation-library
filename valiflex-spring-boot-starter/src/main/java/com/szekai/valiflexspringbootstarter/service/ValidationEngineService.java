package com.szekai.valiflexspringbootstarter.service;

import com.szekai.valiflexspringbootstarter.config.RulesProperties;
import com.szekai.valiflexspringbootstarter.exception.ValidationException;
import com.szekai.valiflexspringbootstarter.service.rules.ValidationRule;
import com.szekai.valiflexspringbootstarter.vo.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ValidationEngineService {

    private final Map<String, ValidationRule> validationRuleMap;
    private final RulesProperties rulesConfig;

    @Autowired
    public ValidationEngineService(Map<String, ValidationRule> validationRuleMap, RulesProperties rulesConfig) {
        this.validationRuleMap = validationRuleMap;
        this.rulesConfig = rulesConfig;
    }

    public ValidationResult validateTransaction(Transaction transaction, boolean parallel) throws ValidationException {
        List<String> ruleNames = rulesConfig.types().get(transaction.getTtType());
        if (ruleNames == null) {
            throw new ValidationException("Unknown TT type: " + transaction.getTtType());
        }

        List<ValidationRule> rules = ruleNames.stream()
                .map(validationRuleMap::get)
                .collect(Collectors.toList());

        ValidationResult result = new ValidationResult();
        if (parallel) {
            rules.parallelStream().forEach(rule -> executeRule(rule, transaction, result));
        } else {
            for (ValidationRule rule : rules) {
                executeRule(rule, transaction, result);
                if (!result.isValid()) {
                    break;
                }
            }
        }
        logResult(result);
        return result;
    }

    private void executeRule(ValidationRule rule, Transaction transaction, ValidationResult result) {
        try {
            rule.validate(transaction);
            result.addPassedRule(rule.getClass().getSimpleName());
        } catch (ValidationException e) {
            result.addFailedRule(rule.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void logResult(ValidationResult result) {
        if (result.isValid()) {
            log.info("Validation passed for rules: {}", result.getPassedRules());
        } else {
            log.error("Validation failed for rules: {}", result.getFailedRules());
            log.error("Error messages: {}", result.getErrorMessages());
        }
    }
}
