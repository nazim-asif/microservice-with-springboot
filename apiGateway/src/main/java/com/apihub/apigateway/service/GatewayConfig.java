package com.apihub.apigateway.service;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author Nazim Uddin Asif
 * @Since 1.0.0
 */
@Configuration
public class GatewayConfig {

    @Bean
    public CustomKeyResolver customKeyResolver() {
        return new CustomKeyResolver();
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, RateLimitFilter rateLimitFilter) {
        return builder.routes()
                .route("CUSTOMER-INFO-INTEGRATION-SERVICE", r -> r
                        .path("/api/v1/integration/**")
                        .filters(f -> f.filter(rateLimitFilter)) // Add the rateLimitFilter for this route
                        .uri("lb://CUSTOMER-INFO-INTEGRATION-SERVICE")
                )
                .build();
    }
}
