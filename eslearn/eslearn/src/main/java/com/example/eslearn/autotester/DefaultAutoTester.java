package com.example.eslearn.autotester;

import com.example.eslearn.models.TestRequestInfo;
import com.example.eslearn.models.TestResult;
import com.example.eslearn.saver.Saver;
import com.example.eslearn.sender.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class DefaultAutoTester implements AutoTester {

    @Autowired
    private Sender sender;

    @Autowired
    private Saver saver;

    @Override
    public void sendAndReceive(TestRequestInfo requestInfo) {
        log.info("test begin:");
        log.info(requestInfo.toString());
        TestResult result =  this.test(sender, requestInfo);
        log.info("after test:");
        log.info(result.toString());
        this.saver.save(result);
    }

}
