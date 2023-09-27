package com.niit.bej.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routePredicate -> routePredicate.path("/api/v1/auth/**")
                        .uri("http://localhost:8081"))
                .route(routePredicate -> routePredicate.path("/api/v1/userService/**")
                        .uri("http://localhost:8082"))
                .route(routePredicate -> routePredicate.path("/api/v1/restaurant/**")
                        .uri("http://localhost:8083"))
                .route(routePredicate -> routePredicate.path("/api/v1/cuisine/**")
                        .uri("http://localhost:8083"))
                .route(routePredicate -> routePredicate.path("/api/v1/order/**")
                        .uri("http://localhost:8084"))
                .route(routePredicate -> routePredicate.path("/api/v1/payment/**")
                        .uri("http://localhost:8085"))
                .build();
    }

}
