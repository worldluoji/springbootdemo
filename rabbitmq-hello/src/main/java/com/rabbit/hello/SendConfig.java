package com.rabbit.hello;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SendConfig {

    @Bean
    public Queue luojiQueue() {
        return new Queue("hello-luoji-queue");
    }
}
