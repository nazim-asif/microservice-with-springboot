package com.apihub.customerinfo.service;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Configuration
public class CircuitBreakerBeanConfiguration {

    @Autowired
    private CircuitBreakerConfig customCircuitBreakerConfig;

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(customCircuitBreakerConfig);
        return circuitBreakerRegistry.circuitBreaker("myCircuitBreaker");
    }
}
