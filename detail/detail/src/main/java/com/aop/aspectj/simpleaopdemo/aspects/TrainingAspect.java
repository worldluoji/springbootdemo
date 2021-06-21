package com.aop.aspectj.simpleaopdemo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import lombok.extern.slf4j.Slf4j;

// 定义一个切面
@Slf4j
@Aspect
public class TrainingAspect {
    // 指定切点(什么时候会进行拦截)，execution表示执行方法train()时会执行， *表示任意返回类型的方法，..代表任意参数,
    // train()方法就是一个连接点
    @Pointcut("execution(* com.aop.aspectj.simpleaopdemo.impls.SimpleTraining.train(..))")
    public void doTrain() {

    }

    @Pointcut("execution(* com.aop.aspectj.simpleaopdemo.impls.SimpleTraining.trainWithMate(..)) " + " && args(mateName)")
    public void doTrainWithMate(String mateName) {

    }

    // 以下是四种通知
    @Before("doTrain()")
    public void before() {
       log.info("before train...");
    }

    @Before("doTrain()")
    public void bef() {
        log.info("bef train...");
    }

    @After("doTrain()")
    public void after() {
        log.info("after train...");
    }

    // 环绕通知可以同时进行前置和后置通知，当然也可以再jp.proceed()执行前return中止后续操作
    @Around("doTrain()")
    public void around(ProceedingJoinPoint jp) {
        System.out.println("运动前要进行热身运动");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("运动后要补充水分并进行肌肉拉伸");
    }

    @AfterReturning("doTrain()")
    public void afterReturning() {
        log.info("运动全部完成");
    }

    @AfterThrowing("doTrain()")
    public void afterThrowing() {
        log.warn("今天状态不好，天气炎热，运动终止");
    }

    //jp一定更要写在第一个参数，否则会报illegalStateException
    @Around("doTrainWithMate(mateName)")
    public void aroundWithMate(ProceedingJoinPoint jp, String mateName) {
        log.info(mateName, "是新手，记得给她买水...");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("运动结束，带", mateName, "去吃顿大餐");
    }

}