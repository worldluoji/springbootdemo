package com.aop.aspectj.simpleaopdemo.config;

import com.aop.aspectj.simpleaopdemo.aspects.TrainingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ComponentScan("com.aop.aspectj.simpleaopdemo.aspects")
public class AopConfig {
    @Bean
    public TrainingAspect getTrainingAspect() {
        return new TrainingAspect();
    }
}
