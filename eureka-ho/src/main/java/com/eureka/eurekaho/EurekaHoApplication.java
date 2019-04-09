package com.eureka.eurekaho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaHoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHoApplication.class, args);
    }

}
