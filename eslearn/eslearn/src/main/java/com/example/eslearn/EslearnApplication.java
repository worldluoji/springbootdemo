package com.example.eslearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EslearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EslearnApplication.class, args);
	}

}
