package com.aop.aspectj.simpleaopdemo;

import com.aop.aspectj.simpleaopdemo.interfaces.ITraining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAopDemoApplication implements ApplicationRunner {

    @Autowired
    @Qualifier("SimpleTraining")
    private ITraining training;

    public static void main(String[] args) {
        SpringApplication.run(SimpleAopDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //training.train();
        training.trainWithMate("zy");
    }
}
