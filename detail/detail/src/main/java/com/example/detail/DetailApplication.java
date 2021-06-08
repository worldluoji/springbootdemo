package com.example.detail;


import com.beanissues.Case1Service;
import com.beanissues.Case2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {@ComponentScan(value = "com.beanissues"), @ComponentScan(value = "com.example.detail")})
@SpringBootApplication
public class DetailApplication implements ApplicationRunner {

	@Autowired
	private Case1Service case1;

	@Autowired
	private Case2Service case2;


	public static void main(String[] args) {
		SpringApplication.run(DetailApplication.class, args);
		// ConfigurableApplicationContext context = SpringApplication.run(DetailApplication.class, args);
		// context.close(); // 将上下文关闭，即关闭当前的 Spring 容器
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		case1.say();
		case2.say();
	}

}
