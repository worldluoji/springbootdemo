package com.example.detail.events;

import org.springframework.context.ApplicationEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleEvent extends ApplicationEvent {

    public SimpleEvent(Object source) {
        super(source);
    }

    public void doEvent() {
        if (source instanceof String) {
            log.info((String)source);
        }  else {
            log.info("trigger SimpleEvent");
        }
    }
}
