package com.aop.aspectj.simpleaopdemo.impls;

import com.aop.aspectj.simpleaopdemo.interfaces.ITraining;
import org.springframework.stereotype.Component;

@Component("simpleTraining")
public class SimpleTraining implements ITraining {
    @Override
    public void train() {
        System.out.println("轻松跑步，快乐健身...");
    }

    @Override
    public void trainWithMate(String mateName) {
        System.out.println("和" + mateName + "一起运动...");
    }
}
