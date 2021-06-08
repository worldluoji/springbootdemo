package com.example.detail.controllers;

import com.beanissues.Case3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Case3Service case3;

    @Autowired
    private ApplicationContext applicationContext;

    /**
    * 不管我们访问多少次http://localhost:8080/hello，访问的结果都是不变的,
    * 这与Case3Service prototype设定背道而驰
    **/
    @GetMapping(path="hello")
    public String hello() {
        return "hello : " + case3;
    }

    /**
    * 这种方式可以解决上面的问题
    **/
    @GetMapping(path = "hi")    
    public String hi() {
        return "helloworld, service is : " + getServiceImpl();    
    };

    public Case3Service getServiceImpl() {        
        return applicationContext.getBean(Case3Service.class);    
    }
}
