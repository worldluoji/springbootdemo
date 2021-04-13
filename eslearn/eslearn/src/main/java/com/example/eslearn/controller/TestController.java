package com.example.eslearn.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.eslearn.autotester.AutoTester;
import com.example.eslearn.models.TestRequestInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    
    @Value("${SERVER_ADDRESSES}")
    private List<String> servers;
    
    @Value("${SERVER_PORT}")
    private String port;

    @Autowired
    private AutoTester autoTester;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String autoTest() {
        for (String server : servers) {
            String finalServer = new StringBuilder("http://")
                                    .append(server)
                                    .append(":")
                                    .append(port).toString();
            TestRequestInfo requestInfo = new TestRequestInfo();
            requestInfo.setServerAddr(finalServer);
            requestInfo.setSystem("TestServer");
            requestInfo.setTransCode("TESTTEST");
            requestInfo.setSendTime(LocalDateTime.now());
            requestInfo.setContent("");
            requestInfo.setFlowId(UUID.randomUUID().toString());
            autoTester.sendAndReceive(requestInfo);
        }
        return "success";
    }

}
