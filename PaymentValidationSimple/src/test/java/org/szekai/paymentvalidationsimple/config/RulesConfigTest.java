package org.szekai.paymentvalidationsimple.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.szekai.paymentvalidationsimple.service.rules.ValidationRule;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RulesConfigTest {

    @Autowired
    private Map<String, ValidationRule> rules;

    @Autowired
    private RulesConfig rulesConfig;
    @Test
    void config(){
        System.out.println(rules);
        System.out.println(rulesConfig.getTypes());
    }
}