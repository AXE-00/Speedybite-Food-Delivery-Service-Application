package com.niit.bej.user.service;

import com.niit.bej.user.service.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
@EnableFeignClients
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new JwtFilter());
		registration.addUrlPatterns(
				"/api/v1/userService/get/profile",
				"/api/v1/userService/update/user",
				"/api/v1/userService/getName",
				"/api/v1/userService/add/item",
				"/api/v1/userService/get/user/favourite",
				"/api/v1/userService/check/list",
				"/api/v1/userService/remove/favourite",
				"/api/v1/userService/add/address",
				"/api/v1/userService/get/address"
		);
		return registration;
	}
}
