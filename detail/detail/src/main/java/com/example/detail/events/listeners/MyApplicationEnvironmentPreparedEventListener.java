package com.example.detail.events.listeners;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent > {

    public void onApplicationEvent(final ApplicationEnvironmentPreparedEvent event) {
        log.info("{} received: {}", this.toString(), event);
    }

}