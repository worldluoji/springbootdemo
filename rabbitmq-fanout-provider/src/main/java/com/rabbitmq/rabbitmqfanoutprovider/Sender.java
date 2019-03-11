package com.rabbitmq.rabbitmqfanoutprovider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException {
        System.out.println("Before send");
        this.rabbitmqTemplate.convertAndSend(this.exchange,"","This is fanout message");
        System.out.println("After send");
    }
}
