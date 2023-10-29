package com.apihub.customerinfo.utils;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Service
public class CustomCircuitBreakerService {
    private final Map<String, CircuitBreaker> circuitBreakers = new ConcurrentHashMap<>();



    public void createCircuitBreaker(String name, int failureRateThreshold, int minimumNumberOfCalls) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .minimumNumberOfCalls(minimumNumberOfCalls)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(4)
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of(name, circuitBreakerConfig);
        circuitBreakers.put(name, circuitBreaker);
    }

    public ResponseEntity<String> executeWithCircuitBreaker(String serviceName, CheckedFunction0<ResponseEntity<String>> businessLogic) {
        CircuitBreaker circuitBreaker = circuitBreakers.get(serviceName);
        if (circuitBreaker != null) {
            try {
                return circuitBreaker.executeCheckedSupplier(businessLogic);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("CircuitBreaker not found for service: " + serviceName);
        }
    }
}