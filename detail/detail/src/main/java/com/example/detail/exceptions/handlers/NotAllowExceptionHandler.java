package com.example.detail.exceptions.handlers;

import com.example.detail.exceptions.NotAllowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class NotAllowExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotAllowException.class)
    public String handle() {
        log.info("403");
        return "{\"resultCode\": 403}";
    }
}
