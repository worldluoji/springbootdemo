package com.example.detail.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigGuration {
    @Bean
    public String serviceName() {
        return "MyService";
    }
}
