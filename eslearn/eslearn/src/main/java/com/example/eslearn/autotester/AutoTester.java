package com.example.eslearn.autotester;

import java.time.LocalDateTime;

import com.example.eslearn.models.TestRequestInfo;
import com.example.eslearn.models.TestResult;
import com.example.eslearn.sender.Sender;

public interface AutoTester {
    default void check(TestResult result) {
        String content = result.getContent();
        if (content == null || content.isEmpty()) {
            StringBuilder builder = new StringBuilder(result.getSystem())
                                        .append(":[")
                                        .append(result.getTransCode())
                                        .append("]")
                                        .append("returned nothing");
            result.setContent(builder.toString());
            return;
        }
        // deault just make success true
        result.setSuccess(true);
        
    }

    default TestResult test(Sender sender, TestRequestInfo requestInfo) {
        if (sender == null || requestInfo == null) {
            throw new IllegalArgumentException("sender or requestInfo is null");
        }
        String resStr = sender.send(requestInfo);
        TestResult result = new TestResult();
        result.setContent(resStr);
        result.setFlowId(requestInfo.getFlowId());
        result.setSystem(requestInfo.getSystem());
        result.setTransCode(requestInfo.getTransCode());
        result.setReceivedTime(LocalDateTime.now());
        result.setSendTime(requestInfo.getSendTime());
        this.check(result);
        return result;
    }

    void sendAndReceive(TestRequestInfo requestInfo);
}
