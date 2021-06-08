package com.example.detail.services.lifecycle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LightMgrService implements InitializingBean {
    @Autowired
    private LightService lightService;
    public LightMgrService() {
        log.info("[LightMgrService]constructor");
    }

    @PostConstruct
    public void init() {
        lightService.check();
    }

    public void start() {
        lightService.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[LightMgrService]afterPropertiesSet");
    }
}