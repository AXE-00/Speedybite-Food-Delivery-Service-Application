package com.niit.bej.api.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClient(name = "payment-service")
public class PaymentServiceConfig {
    @Bean
    @LoadBalanced
    public RouteLocator paymentServiceRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(routePredicate -> routePredicate.path("/api/v1/payment/**")
                        .uri("lb://payment-service"))
                .build();
    }
}
