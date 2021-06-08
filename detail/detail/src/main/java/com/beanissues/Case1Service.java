package com.beanissues;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Case1Service {
    public void say() {
        log.info("i am a bean out of the package of Application.java");
    }
}
