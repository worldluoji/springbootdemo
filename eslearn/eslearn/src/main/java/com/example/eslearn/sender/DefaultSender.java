package com.example.eslearn.sender;

import com.example.eslearn.models.TestRequestInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Primary
public class DefaultSender implements Sender {

    @Autowired
    private RestTemplate rest;

    @Override
    public String send(TestRequestInfo requestInfo) {
        // String content = requestInfo.getContent();
        // if (content == null || content.isEmpty()) {
        //     StringBuilder builder = new StringBuilder(requestInfo.getSystem())
        //                                 .append(":[")
        //                                 .append(requestInfo.getTransCode())
        //                                 .append("]input content is empty");
        //     throw new IllegalArgumentException(builder.toString());
        // }
        ResponseEntity<String> result = rest.getForEntity(requestInfo.getServerAddr(), String.class);
        log.info(result.getBody());
        return result.getBody();
    }
    
}
