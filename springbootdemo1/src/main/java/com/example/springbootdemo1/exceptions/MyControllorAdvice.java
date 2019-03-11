package com.example.springbootdemo1.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllorAdvice {

    /*
     * 全局异常处理，只要使用了@RequestMapping注解的方法， 所有异常都会被捕获到
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map<String, Object> errorHandler(BusinessException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
