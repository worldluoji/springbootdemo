package com.example.springbootlistenerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 简单事件监听流程：
 * 1.自定义事件，一般是继承ApplicationEvent抽象类
 * 2.定义事件监听器，一般是实现ApplicationListener接口
 * 3.启动的时候，需要把监听器加入到spring容器中
 * 4.发布事件：ApplicationContext.publishEvent发布事件
 */
@SpringBootApplication
public class SpringbootListenerDemoApplication implements ApplicationRunner {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootListenerDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String msg = "通知：由于疫情影响，请各个地区做好防御措施";
		applicationContext.publishEvent(new SimpleEvent(msg));
	}
}
