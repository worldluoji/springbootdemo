package com.rabbit.hello;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues="hello-luoji-queue")
    public void process(String msg) {
        System.out.println(msg);
    }
}
