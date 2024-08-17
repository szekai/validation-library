package com.szekai.valiflexspringbootstarter.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValiflexConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ValiflexConfiguration.class))
            .withPropertyValues("rules-config.types.typeB=checkTTTypeRule");

    @Test
    void shouldContainTodoRestClientBean() {
        contextRunner.run(context -> {
//            assertTrue(context.containsBean(RulesProperties.class.getCanonicalName()));
            assertTrue(context.containsBean("rulesMap"));
        });
    }

    @Test
    void shouldContainRules() {
        contextRunner.run(context -> {
            assertThat(context.getBean(RulesProperties.class).types().toString()).isEqualTo("{typeB=[checkTTTypeRule]}");
        });
    }
}