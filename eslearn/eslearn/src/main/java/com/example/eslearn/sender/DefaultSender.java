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
        ResponseEntity<String> result;
        try {
            result = rest.getForEntity(requestInfo.getServerAddr(), String.class);
        } catch (Exception e) {
            String errmsg = new StringBuilder("fail to send to ")
                                .append(requestInfo.getSystem())
                                .append(",time:")
                                .append(requestInfo.getSendTime())
                                .append("\r\n")
                                .append(e.toString()).toString();
            log.error("fail to send to ", requestInfo.getSystem(), requestInfo.getSendTime(), e);
            return errmsg;
        }
       
        return result != null ? result.getBody() : "";
    }
    
}
