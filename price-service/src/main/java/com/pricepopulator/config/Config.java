package com.pricepopulator.config;

import com.google.gson.Gson;
import com.pricepopulator.exception.ErrorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Gson gson(){
        return new Gson();
    }

    @Bean
    ErrorBuilder errorBuilder(){
        return new ErrorBuilder();
    }

}
