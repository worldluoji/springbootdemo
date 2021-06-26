package com.example.detail.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/exp")
public class ExceptionController {
    @GetMapping(path="/f1")
    public String f1(@RequestParam("name") String name) {
        return "hello " + name;
    }
}
