package com.example.detail.controllers;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping(path = "/p/hi6")
    public String hi6(@RequestParam("Date") Date date){
        return "date is " + date ;
    };

    @GetMapping(path = "/p/hi7")
    public String hi7(@RequestParam("Date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date){
        return "date is " + date ;
    };
}
