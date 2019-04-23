package com.feign.eureka.simpleeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SimpleEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleEurekaServerApplication.class, args);
    }

}
