package com.example.detail.controllers;

import com.example.detail.models.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/body")
public class RequestBodyController {
    @PostMapping(path="/level")
    public String getUserLevel(@RequestBody User user) {
        long payNum =  Long.parseLong(user.getPayNum());
        if (payNum == 0) {
            return "low";
        } else if (payNum > 300) {
            return "high";
        }
        return "middle";
    }
}
