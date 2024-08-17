package org.szekai.paymentvalidationsimple.config;

import com.szekai.valiflexspringbootstarter.config.RulesProperties;
import com.szekai.valiflexspringbootstarter.service.rules.ValidationRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RulesConfigTest {

    @Autowired
    private Map<String, ValidationRule> rules;

    @Autowired
    private RulesProperties rulesConfig;
    @Test
    void config(){
        System.out.println(rules);
        System.out.println(rulesConfig.types());
    }
}