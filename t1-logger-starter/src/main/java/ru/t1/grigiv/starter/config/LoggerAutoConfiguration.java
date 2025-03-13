package ru.t1.grigiv.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.grigiv.starter.aspect.LoggerAspect;

@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerAutoConfiguration {

    private final LoggerProperties loggerProperties;

    public LoggerAutoConfiguration(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    @Bean
    @ConditionalOnProperty(
            value = "logger.enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public LoggerAspect loggerAspect(LoggerProperties loggerProperties) {
        return new LoggerAspect(loggerProperties);
    }
}
