package com.aop.aspectj.simpleaopdemo.aspects;

import java.lang.reflect.Method;

import com.example.detail.annotations.DataSourceSelect;
import com.example.detail.configs.MyDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


/*****
* 特别要注意的是，这里要加上一个 @Order(1) 标记它的初始化顺序。这个 Order 值一定要比事务的 AOP 切面的值小，
* 这样可以获得更高的优先级，否则自动切换数据源将会失效
*****/
@Slf4j
@Aspect
@Service
@Order(1)
public class DataSourceSwitch {

    @Around("execution(* com.example.detail.services.CardService.*(..))")
    public void aroundCard(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DataSourceSelect.class)) {
            DataSourceSelect dataSource = method.getAnnotation(DataSourceSelect.class);
            MyDataSource.setDataSource(dataSource.value());
            log.info("数据源切换至："+MyDataSource.getDatasource());
        }
        point.proceed();
        MyDataSource.clearDataSource();
        log.info("数据源已移除！");
    }
}