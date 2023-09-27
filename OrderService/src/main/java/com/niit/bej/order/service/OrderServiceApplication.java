package com.niit.bej.order.service;

import com.niit.bej.order.service.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> orderFilter() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>(new JwtFilter());
        bean.addUrlPatterns(
                "/api/v1/order/insertOrder/*",
                "/api/v1/order/placeOrder/*",
                "/api/v1/order/cancelOrder/*",
                "/api/v1/order/removeItem/*",
                "/api/v1/order/addItem/*");
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return bean;
    }
}
