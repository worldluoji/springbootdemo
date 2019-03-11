package com.rabbitmq.topicprovider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserSend {

    @Value("${mq.config.exchange}")
    private String exchange;
    
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() throws InterruptedException {
        this.rabbitTemplate.convertAndSend(this.exchange, "user.log.debug","user log debug...");
        this.rabbitTemplate.convertAndSend(this.exchange, "goods.log.debug","user log debug...");
        this.rabbitTemplate.convertAndSend(this.exchange, "user.log.info","user log debug...");
        this.rabbitTemplate.convertAndSend(this.exchange, "flower.log.error","user log debug...");
    }
}
