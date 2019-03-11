package com.rabbitmq.direct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    @Value("${mq.config.queue.error.routing.key}")
    private String routingKey;

    public void send() throws InterruptedException {
        String msg = "Hello" + new Date();
        // 交换器，路由键，消息内容
        this.rabbitmqTemplate.convertAndSend(this.exchange, this.routingKey, msg);
    }
}
