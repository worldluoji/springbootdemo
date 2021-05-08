package com.example.detail;


import com.case1.Case1Service;
import com.case1.Case2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {@ComponentScan(value = "com.case1"), @ComponentScan(value = "com.example.detail")})
@SpringBootApplication
public class DetailApplication implements ApplicationRunner {

	@Autowired
	private Case1Service case1;

	@Autowired
	private Case2Service case2;


	public static void main(String[] args) {
		SpringApplication.run(DetailApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		case1.say();
		case2.say();
	}

}
