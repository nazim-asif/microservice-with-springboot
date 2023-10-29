package com.apihub.customerinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import org.springframework.context.annotation.Bean;

import java.time.Duration;

import java.time.Duration;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CustomerInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerInfoApplication.class, args);
    }

/*    @Bean
    public Customizer<Resilience4JConfigBuilder> globalCustomConfiguration() {
        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(4))
                .build();
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build());
    }*/

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
//
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofSeconds(4))
//                .build();
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
//                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
//    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration2() {
//
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofSeconds(4))
//                .build();
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
//                        .timeLimiterConfig(timeLimiterConfig).build(),
//                "circuitBreaker1", "circuitBreaker2", "circuitBreaker3");
//    }

}
