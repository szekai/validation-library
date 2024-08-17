package com.szekai.valiflexspringbootstarter.config;

import com.szekai.valiflexspringbootstarter.service.ValidationEngineService;
import com.szekai.valiflexspringbootstarter.service.rules.ValidationRule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AutoConfiguration
@EnableConfigurationProperties(RulesProperties.class)
public class ValiflexConfiguration {

//    private final RulesProperties rulesConfig;

//    public ValiflexConfiguration(RulesProperties rulesConfig) {
//        this.rulesConfig = rulesConfig;
//    }
    @Bean
    public Map<String, ValidationRule> rulesMap(List<ValidationRule> rules){
        return rules.stream().collect(Collectors.toMap(s -> s.getClass().getSimpleName(), s -> s));
    }

    @Bean
    public ValidationEngineService validationEngineService(Map<String, ValidationRule> validationRuleMap, RulesProperties rulesConfig){
        return new ValidationEngineService(validationRuleMap, rulesConfig);
    }
//    @Bean
//    public RulesProperties rulesProperties(){
//        return rulesConfig;
//    }
}
