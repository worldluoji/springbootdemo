package com.example.detail.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
    
    /*
    * 正常接收头部中的单个参数
    */
    @GetMapping(path = "/h/hi1")
    public String hi1(@RequestHeader("myHeader") String name){
        return "hello " + name;
    };

     /*
    * 正常接收头部中的单个参数，
    * 有相同key值的多个参数只会接收第一个
    */
    @GetMapping(path = "/h/hi2")
    public String hi2(@RequestHeader("myHeader") Map<String,String> map){
        return map.toString();
    };

    /*
    * 正常接收头部中的单个参数，
    * 有相同key值的多个参数全部接收
    */
    @GetMapping(path = "/h/hi3")
    public String hi3(@RequestHeader("myHeader") MultiValueMap<String,String> map){
        return map.toString();
    };

    /*
    * 正常接收头部中的单个参数，
    * 有相同key值的多个参数全部接收，HttpHeaders本身也是一个MultiValueMap
    */
    @GetMapping(path = "/h/hi4")
    public String hi4(@RequestHeader("myHeader") HttpHeaders map){
        return map.toString();
    };


    /*
    * 返回中设置contenttype无效？
    */
    @GetMapping(path = "/h/hi5")
    public String hi5(HttpServletResponse httpServletResponse){
    httpServletResponse.addHeader("myheader", "myheadervalue");
    httpServletResponse.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return "ok";
    };

    @GetMapping(path = "/h/hi6",produces = {"application/json"})
    public String hi6(){
        return "ok";
    };
}
