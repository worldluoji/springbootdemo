package com.example.detail.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
public class PathVariableController {

    /*
    * http://localhost:8080/hi1/xiao/ming 404
    * http://localhost:8080/hi1/xiaoming 正常返回 
    */
    @GetMapping(path = "/hi1/{name}")
    public String hello1(@PathVariable String name) {
        return "Hello " + name;
    }

    /*
    * http://localhost:8080/hi2/xiao/ming 正常返回xiao/ming
    * http://localhost:8080/hi2/xiaoming 正常返回 
    * 但这个方案仍然不够完美，/hi2/xiao/hi2/ming就会有问题
    */
    @GetMapping(path = "/hi2/**")
    public String hello2(HttpServletRequest request) {
        // reusetURI = "/hi2/xiao/ming"
        String requestURI = request.getRequestURI();    
        return "Hello " + requestURI.split("/hi2/")[1];
    }

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @GetMapping(path = "/hi3/**")
    public String hello3(HttpServletRequest request) {
        // http://localhost:8080/hi3/xiao/hi3/ming -> path = "/hi3/xiao/hi3/ming"
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        //matchPattern 即为"/hi3/**"
        String matchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);     
        return antPathMatcher.extractPathWithinPattern(matchPattern, path);
    }
}
