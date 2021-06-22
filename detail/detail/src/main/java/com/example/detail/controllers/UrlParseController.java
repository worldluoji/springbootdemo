package com.example.detail.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlParseController {
    @GetMapping(path = "/p/hi1")
    public String hi1(@RequestParam("name") String name){
        return name;
    };

    @GetMapping(path = "/p/hi2")
    public String hi2(@RequestParam String name){
        return name;
    };
}
