package com.apihub.customerinfo.utils;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Component
public class CircuitBreakerHealthIndicator extends AbstractHealthIndicator {
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerHealthIndicator(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        Map<String, CircuitBreaker> circuitBreakers = circuitBreakerRegistry.getAllCircuitBreakers()
                .collect(Collectors.toMap(CircuitBreaker::getName, c -> c));

        for (Map.Entry<String, CircuitBreaker> entry : circuitBreakers.entrySet()) {
            CircuitBreaker circuitBreaker = entry.getValue();
            builder.withDetail("circuitBreakers." + entry.getKey() + ".status", circuitBreaker.getState().toString())
                    .withDetail("circuitBreakers." + entry.getKey() + ".failureRate", circuitBreaker.getMetrics().getFailureRate())
                    .withDetail("circuitBreakers." + entry.getKey() + ".failureRateThreshold", circuitBreaker.getCircuitBreakerConfig().getFailureRateThreshold())
                    .withDetail("circuitBreakers." + entry.getKey() + ".slowCallRate", circuitBreaker.getMetrics().getSlowCallRate())
                    .withDetail("circuitBreakers." + entry.getKey() + ".slowCallRateThreshold", circuitBreaker.getCircuitBreakerConfig().getSlowCallRateThreshold())
                    .withDetail("circuitBreakers." + entry.getKey() + ".bufferedCalls", circuitBreaker.getMetrics().getNumberOfBufferedCalls())
                    .withDetail("circuitBreakers." + entry.getKey() + ".slowCalls", circuitBreaker.getMetrics().getNumberOfSlowCalls())
                    .withDetail("circuitBreakers." + entry.getKey() + ".slowFailedCalls", circuitBreaker.getMetrics().getNumberOfSlowFailedCalls())
                    .withDetail("circuitBreakers." + entry.getKey() + ".failedCalls", circuitBreaker.getMetrics().getNumberOfFailedCalls())
                    .withDetail("circuitBreakers." + entry.getKey() + ".notPermittedCalls", circuitBreaker.getMetrics().getNumberOfNotPermittedCalls());
        }
    }

}
