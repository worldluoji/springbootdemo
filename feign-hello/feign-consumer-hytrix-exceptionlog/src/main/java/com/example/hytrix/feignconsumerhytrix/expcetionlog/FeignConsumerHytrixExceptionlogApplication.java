package com.example.hytrix.feignconsumerhytrix.expcetionlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker //开启服务降级 断路器
@SpringBootApplication
public class FeignConsumerHytrixExceptionlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerHytrixExceptionlogApplication.class, args);
    }
}
