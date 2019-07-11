package com.springboot.autoconfig.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class GreetingApplication implements ApplicationRunner {
    public GreetingApplication() {
        log.info("init GreetingApplication.");
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("hello everyone, we are all like spring.");
    }
}
