package com.example.detail.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.detail.services.lifecycle.LightMgrService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class LifecycleController {

    @Autowired
    private LightMgrService lightMgrService;

    @GetMapping(path="light")
    public String light() {
        lightMgrService.start();
        return "success";
    }

}
