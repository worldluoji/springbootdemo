package com.example.springbootdemo1.controller;

import com.example.springbootdemo1.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${luoji.msg2}")
    private String msg;

    @RequestMapping("/hello")
    public String index() {
        //System.out.println(1/0);
        throw new BusinessException("300", "用户名密码错误");
        //return this.msg;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
