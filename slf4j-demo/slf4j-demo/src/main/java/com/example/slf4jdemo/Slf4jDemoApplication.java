package com.example.slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
*  SpringBoot集成了slf4j
 * slf4j定义了日志接口的一组规范，默认就是使用logback,也可以替换为log4j
 * 如果在resources目录下放上每个日志框架自己的配置文件，比如例子中logback-spring.xml文件
 * SpringBoot就不会使用默认的配置了
* */
@SpringBootApplication
public class Slf4jDemoApplication implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(Slf4jDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("测试日志...");
	}
}
