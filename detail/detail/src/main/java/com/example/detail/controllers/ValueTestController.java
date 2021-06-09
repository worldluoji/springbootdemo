package com.example.detail.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueTestController {
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @GetMapping(path = "user")
    public String getUser(){ 
        return username + ":" + password; 
    };
}
