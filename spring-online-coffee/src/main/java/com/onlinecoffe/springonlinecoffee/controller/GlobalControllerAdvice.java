package com.onlinecoffe.springonlinecoffee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> validationExceptionHandler(ValidationException exp) {
        Map<String, String> map =  new HashMap<>();
        map.put("message", exp.getMessage());
        return map;
    }
}
