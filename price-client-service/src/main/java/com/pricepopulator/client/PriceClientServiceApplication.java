package com.pricepopulator.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
@RefreshScope
@EnableEurekaClient
@EnableCircuitBreaker
public class PriceClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceClientServiceApplication.class, args);
	}
}
