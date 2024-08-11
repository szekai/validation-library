package com.szekai.validatationengine.service;

import com.szekai.validatationengine.config.RulesConfig;
import com.szekai.validatationengine.exception.ValidationException;
import com.szekai.validatationengine.service.rules.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationEngineService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationEngineService.class);

    private final Map<String, ValidationRule> validationRuleMap;
    private final RulesConfig rulesConfig;

    @Autowired
    public ValidationEngineService(Map<String, ValidationRule> validationRuleMap, RulesConfig rulesConfig) {
        this.validationRuleMap = validationRuleMap;
        this.rulesConfig = rulesConfig;
    }

    public ValidationResult validateTransaction(Transaction transaction, boolean parallel) throws ValidationException {
        List<String> ruleNames = rulesConfig.getTypes().get(transaction.getTtType());
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
            logger.error("Validation failed: {} - {}", rule.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void logResult(ValidationResult result) {
        if (result.isValid()) {
            logger.info("Validation passed for rules: {}", result.getPassedRules());
        } else {
            logger.error("Validation failed for rules: {}", result.getFailedRules());
            logger.error("Error messages: {}", result.getErrorMessages());
        }
    }
}
