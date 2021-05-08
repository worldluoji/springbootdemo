package com.case1;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Case2Service {

    private String serviceName;

    public Case2Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public void say() {
        log.info(this.serviceName);
    }

}
