package com.pricepopulator.priceconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigServer
public class PriceConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceConfigServiceApplication.class, args);
	}
}
