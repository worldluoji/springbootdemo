package com.example.eslearn.saver;

import com.example.eslearn.models.TestResult;
import com.example.eslearn.services.TestResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Primary
public class EsSaver implements Saver {

    @Autowired
    private TestResultService testResultService;

    @Override
    public String save(TestResult result) {
        try {
            testResultService.save(result);
        } catch (Exception e) {
            log.error("[EsSaver]fail to save", e);
            return "";
        }
        
        return result.getFlowId();
    }
    
}
