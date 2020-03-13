package com.example.springbootlistenerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener implements ApplicationListener<SimpleEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(SimpleEvent simpleEvent) {
        logger.info("监听" + simpleEvent.getClass());
        simpleEvent.doEvent();
    }
}
