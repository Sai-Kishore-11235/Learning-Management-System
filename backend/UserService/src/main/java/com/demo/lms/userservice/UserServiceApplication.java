package com.demo.lms.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.demo.lms.userservice.filter.JWTfilter;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean frb = new FilterRegistrationBean<>();
		frb.setFilter(new JWTfilter());
		frb.addUrlPatterns("/api/v1/*");
		return frb;
	}
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
