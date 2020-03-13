package com.example.springbootlistenerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public class SimpleEvent extends ApplicationEvent {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SimpleEvent(Object source) {
        super(source);
    }

    public void doEvent() {
        if (source instanceof String) {
            logger.info((String)source);
        }  else {
            logger.info("触发事件...SimpleEvent");
        }
    }
}
