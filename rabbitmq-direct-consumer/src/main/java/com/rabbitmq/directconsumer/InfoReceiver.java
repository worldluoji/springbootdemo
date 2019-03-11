package com.rabbitmq.directconsumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings=@QueueBinding(
        value=@Queue(value="${mq.config.queue.info}", autoDelete="true"),
        exchange=@Exchange(value="${mq.config.exchange}", type= ExchangeTypes.DIRECT),
        key="${mq.config.queue.info.routing.key}"
))
public class InfoReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receive Info : " + msg);
    }
}
