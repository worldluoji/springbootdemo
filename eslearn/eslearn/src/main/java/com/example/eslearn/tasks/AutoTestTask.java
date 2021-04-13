package com.example.eslearn.tasks;

import java.util.List;

import com.example.eslearn.controller.TestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoTestTask {

    @Value("${SERVER_ADDRESSES}")
    private List<String> servers;
    
    @Value("${SERVER_PORT}")
    private String port;

    @Autowired
    private TestController testController;

    @Scheduled(cron="0 0 0,2,4,6,8,10,12,14,16,18,20,22 * * ? ")   //每个整点执行一次
    public void execute() {
        testController.autoTest();
    }
}
