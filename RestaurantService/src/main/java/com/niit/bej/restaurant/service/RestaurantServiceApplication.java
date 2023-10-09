package com.niit.bej.restaurant.service;

import com.niit.bej.restaurant.service.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterUrl() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter());

        filterRegistrationBean.addUrlPatterns(
                "/api/v1/restaurant/*");
        return filterRegistrationBean;
    }
}
