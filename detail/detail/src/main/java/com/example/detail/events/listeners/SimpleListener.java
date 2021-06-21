package com.example.detail.events.listeners;

import com.example.detail.events.SimpleEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener implements ApplicationListener<SimpleEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(SimpleEvent simpleEvent) {
        logger.info("listening " + simpleEvent.getClass());
        simpleEvent.doEvent();
    }
}
