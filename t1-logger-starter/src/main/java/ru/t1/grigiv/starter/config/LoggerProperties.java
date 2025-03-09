package ru.t1.grigiv.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

@ConfigurationProperties(prefix = "logger")
public class LoggerProperties {

    /**
     * Флаг, определяющий, включено ли логирование.
     * По умолчанию: true.
     */
    private boolean enabled = true;

    /**
     * Уровень логирования: INFO, DEBUG, WARN, ERROR.
     * По умолчанию: INFO.
     */
    private LogLevel level = LogLevel.INFO;

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }
}
