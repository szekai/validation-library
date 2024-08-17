package com.szekai.valiflexspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "rules-config")
public record RulesProperties (Map<String, List<String>> types){





//    public Map<String, List<String>> getRules() {
//        return rules;
//    }
//
//    public void setRules(Map<String, List<String>> rules) {
//        this.rules = rules;
//    }
}
