package com.apihub.customerinfo.service;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .permittedNumberOfCallsInHalfOpenState(4)
                .minimumNumberOfCalls(10)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .slowCallRateThreshold(50)
                .slowCallDurationThreshold(Duration.ofMillis(10))
                .failureRateThreshold(50)
                .build();
    }
}