package com.aop.aspectj.simpleaopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Aspect
public class AdminAspect {
    @Before("execution(* com.example.detail.services.aop.AdminUserService.login(..)) ")
    public void logAdminLogin(JoinPoint pjp) throws Throwable {        
        log.info("! admin login ...");
    }
}
