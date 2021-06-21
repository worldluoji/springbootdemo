package com.example.detail.events.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
* ContextStartedEvent 就是 Spring 内置定义的事件，而 Spring Boot 本身会创建和运维 Context
* 但是下面的代码监听该事件失败
*/
@Slf4j
@Component
public class MyContextStartedEventListener implements ApplicationListener<ContextStartedEvent> {

  public void onApplicationEvent(final ContextStartedEvent event) {
    log.info("{} received: {}", this.toString(), event);
  }

}
