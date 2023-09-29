package com.niit.bej.api.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClient(name = "user-service")
public class UserServiceConfig {
    @Bean
    @LoadBalanced
    public RouteLocator userServicerouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routePredicate -> routePredicate.path("/api/v1/userService/**")
                        .uri("lb://user-service"))
                .build();
    }
}
