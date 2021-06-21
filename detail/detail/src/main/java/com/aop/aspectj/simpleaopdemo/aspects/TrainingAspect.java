package com.aop.aspectj.simpleaopdemo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

// 定义一个切面
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
       System.out.println("before train...");
    }

    @After("doTrain()")
    public void after() {
       System.out.println("after train...");
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
        System.out.println("运动后要补充水分并进行肌肉拉伸");
    }

    @AfterReturning("doTrain()")
    public void afterReturning() {
        System.out.println("运动全部完成");
    }

    @AfterThrowing("doTrain()")
    public void afterThrowing() {
        System.out.println("今天状态不好，天气炎热，运动终止");
    }

    //jp一定更要写在第一个参数，否则会报illegalStateException
    @Around("doTrainWithMate(mateName)")
    public void aroundWithMate(ProceedingJoinPoint jp, String mateName) {
        System.out.println(mateName + "是新手，记得给她买水...");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("运动结束，带" + mateName + "去吃顿大餐");
    }

}