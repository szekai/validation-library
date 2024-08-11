package com.szekai.validatationengine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rules")
public class RulesConfig {

    private Map<String, List<String>> types;

    public Map<String, List<String>> getTypes() {
        return types;
    }

    public void setTypes(Map<String, List<String>> types) {
        this.types = types;
    }
}
