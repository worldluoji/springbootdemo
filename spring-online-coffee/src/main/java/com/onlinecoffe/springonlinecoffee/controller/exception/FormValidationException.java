package com.onlinecoffe.springonlinecoffee.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* 定义一个异常类，带一个BindingResult参数， 发生错误时返回码为BAD_REQUEST=400参数错误
* */
@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormValidationException extends RuntimeException {
    private BindingResult result;
}
