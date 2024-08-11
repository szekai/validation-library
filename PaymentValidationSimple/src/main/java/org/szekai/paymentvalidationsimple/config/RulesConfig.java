package org.szekai.paymentvalidationsimple.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
import java.util.List;

@Configuration
@PropertySource("classpath:rules-config.yml")
@ConfigurationProperties("rules-config")
@Getter
@Setter
public class RulesConfig {

    private Map<String, List<String>> rules;

//    public Map<String, List<String>> getRules() {
//        return rules;
//    }
//
//    public void setRules(Map<String, List<String>> rules) {
//        this.rules = rules;
//    }
}
