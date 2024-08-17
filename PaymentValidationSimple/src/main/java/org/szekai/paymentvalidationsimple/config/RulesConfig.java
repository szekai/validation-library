package org.szekai.paymentvalidationsimple.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.szekai.paymentvalidationsimple.service.rules.ValidationRule;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "rules-config")
@Getter
@Setter
public class RulesConfig {

    private Map<String, List<String>> types;

    @Bean
    public Map<String, ValidationRule> getRulesMap(List<ValidationRule> rules){
        return rules.stream().collect(Collectors.toMap(s -> s.getClass().getSimpleName(), s -> s));
    }

//    public Map<String, List<String>> getRules() {
//        return rules;
//    }
//
//    public void setRules(Map<String, List<String>> rules) {
//        this.rules = rules;
//    }
}
