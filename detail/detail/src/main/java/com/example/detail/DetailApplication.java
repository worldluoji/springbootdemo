package com.example.detail;


import com.aop.aspectj.simpleaopdemo.interfaces.ITraining;
import com.beanissues.Case1Service;
import com.beanissues.Case2Service;
import com.example.detail.events.SimpleEvent;
import com.example.detail.services.ElectricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ServletComponentScan(basePackages={"com.example.detail.filters"})
@ComponentScans(value = {@ComponentScan(value = "com.beanissues"), @ComponentScan(value = "com.example.detail"), @ComponentScan(value = "com.aop")})
@SpringBootApplication
public class DetailApplication implements ApplicationRunner {

	@Autowired
	private Case1Service case1;

	@Autowired
	private Case2Service case2;


	@Autowired
	private ElectricService electricService;


	@Autowired
	@Qualifier("simpleTraining")
	private ITraining training;

	@Autowired
	private ApplicationContext applicationContext;


	public static void main(String[] args) {
		SpringApplication.run(DetailApplication.class, args);
		// ConfigurableApplicationContext context = SpringApplication.run(DetailApplication.class, args);
		// context.close(); // 将上下文关闭，即关闭当前的 Spring 容器
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		case1.say();
		case2.say();
		// electricService.pay();// 空指针
		electricService.payNew();
		applicationContext.publishEvent(new SimpleEvent("start finished"));
		training.train();
	}

}
