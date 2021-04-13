package com.example.eslearn.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.eslearn.autotester.AutoTester;
import com.example.eslearn.models.GeneralTestCase;
import com.example.eslearn.models.TestRequestInfo;
import com.example.eslearn.services.TestcaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class TestController {
    
    @Value("${SERVER_ADDRESSES}")
    private List<String> servers;
    
    @Value("${SERVER_PORT}")
    private String port;

    @Autowired
    private AutoTester autoTester;

    @Autowired
    private TestcaseService testCaseService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value={"/test/autoTest"})
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

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value={"/test/addAndFindTestCases"})
    public String addAndFindTestCases() {
        GeneralTestCase data = new GeneralTestCase();
        data.setAddTime(LocalDateTime.now());
        data.setContent("just for tset " + LocalDateTime.now().toString());
        data.setId(UUID.randomUUID().toString());
        data.setSystem("test");
        this.testCaseService.save(data);
        List<GeneralTestCase> testcases = this.testCaseService.getBySystem("test");
        for (GeneralTestCase testcase : testcases) {
            log.info(testcase.toString());
        }
        return "success";
    }

}
