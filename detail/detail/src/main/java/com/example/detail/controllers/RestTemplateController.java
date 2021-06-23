package com.example.detail.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RestTemplateController {
    /*
    * 请求类型为Post时，使用@RestquestParam为前端表单提交
    */
    @PostMapping(path = "/rest/hi")    
    public String hi(@RequestParam("para1") String para1, @RequestParam("para2") String para2) {        
        return "helloworld:" + para1 + "," + para2;    
    };

    /*/
    * 实际上是将定义的表单数据以 JSON 请求体（Body）的形式提交过去了，
    * 所以我们的接口(hi)处理自然取不到任何表单参数。
    **/
    @GetMapping(path = "/rest/r1")
    public String rest1() {
        RestTemplate template = new RestTemplate();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("para1", "001");
        paramMap.put("para2", "002");

        String url = "http://localhost:8080/rest/hi";
        String result = template.postForObject(url, paramMap, String.class);
        return result;
    }

    @GetMapping(path = "/rest/r2")
    public String rest2() {
        RestTemplate template = new RestTemplate();
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.put("para1", Arrays.asList("001"));
        paramMap.put("para2", Arrays.asList("002"));

        String url = "http://localhost:8080/rest/hi";
        String result = template.postForObject(url, paramMap, String.class);
        return result;
    }

    @GetMapping(path = "/rest/hi2")    
    public String hi2(@RequestParam("para1") String para1) {        
        return "helloworld:" + para1;    
    };

    /*
    * 输出结果helloworld:1，#2被丢了
    */
    @GetMapping(path = "/rest/r3")
    public String rest3() {
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/rest/hi2?para1=1#2";
        HttpEntity<?> entity = new HttpEntity<>(null);
        HttpEntity<String> result = template.exchange(url, HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

    @GetMapping(path = "/rest/r4")
    public String rest4() {
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/rest/hi2?para1=1#2";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(null);
        URI finalUrl = builder.build().encode().toUri();
        HttpEntity<String> result = template.exchange(finalUrl, HttpMethod.GET, entity, String.class);
        return result.getBody();
    }
}
