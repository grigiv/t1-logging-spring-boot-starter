package ru.t1.grigiv.starter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import ru.t1.grigiv.starter.config.LoggerProperties;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    private final LogLevel logLevel;

    public LoggerAspect(LoggerProperties loggerProperties) {
        this.logLevel = loggerProperties.getLevel();
    }

    @Before("@annotation(ru.t1.grigiv.starter.aspect.annotation.LogBefore)")
    public void logBefore() {
        logWithLevel("Перед вызовом метода");
    }

    @AfterReturning("@annotation(ru.t1.grigiv.starter.aspect.annotation.LogAfterReturning)")
    public void logAfterReturning() {
        logWithLevel("Метод выполнен успешно");
    }

    @AfterThrowing(value = "@annotation(ru.t1.grigiv.starter.aspect.annotation.LogAfterThrowing)", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        logWithLevel("Ошибка при выполнении метода: " + exception.getMessage());
    }

    @Around("@annotation(ru.t1.grigiv.starter.aspect.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long elapsedTime = System.currentTimeMillis() - start;
        logWithLevel("Метод " + joinPoint.getSignature() + "выполнен за " + elapsedTime + " мс");
        return result;
    }

    private void logWithLevel(String message) {
        switch (logLevel) {
            case DEBUG:
                logger.debug(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            default:
                logger.info(message);
        }
    }
}