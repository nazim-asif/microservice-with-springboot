package com.apihub.apigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */

@Component
@Slf4j
public class RateLimitFilter implements GatewayFilter {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final CustomKeyResolver customKeyResolver;

    public RateLimitFilter(ReactiveRedisTemplate<String, String> redisTemplate, CustomKeyResolver customKeyResolver) {
        this.redisTemplate = redisTemplate;
        this.customKeyResolver = customKeyResolver;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return customKeyResolver.resolve(exchange)
                .flatMap(key -> {
                    String redisKey = "token_bucket:" + key;

                    return redisTemplate.opsForValue().increment(redisKey, 1)
                            .flatMap(tokens -> {
                                if (tokens == 1) {
                                    // Initialize the bucket with tokens and set expiration
                                    log.info("Initializing token bucket for key: {}", key);
                                    return redisTemplate.expire(redisKey, Duration.ofSeconds(10))
                                            .thenReturn(tokens);
                                } else {
                                    log.info("Current token count for key {}: {}", key, tokens);
                                    return Mono.just(tokens);
                                }
                            })
                            .flatMap(tokens -> {
                                int maxTokens = 5; // Adjust the rate limit as needed
                                if (tokens <= maxTokens) {
                                    log.info("Request allowed for key: {}", key);
                                    return chain.filter(exchange);
                                } else {
                                    log.info("Request rate-limited for key: {}", key);
                                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                                    return exchange.getResponse().setComplete();
                                }
                            });
                });
    }

}
