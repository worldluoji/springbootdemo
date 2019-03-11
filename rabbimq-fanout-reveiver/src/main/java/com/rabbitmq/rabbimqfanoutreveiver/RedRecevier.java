package com.rabbitmq.rabbimqfanoutreveiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings=@QueueBinding(
        value=@Queue(value="${mq.config.queue.red}", autoDelete="true"),
        exchange=@Exchange(value="${mq.config.exchange}", type= ExchangeTypes.FANOUT)
    )
)
public class RedRecevier {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Red Receive:" + msg);
    }
}
