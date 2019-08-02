package com.decorator.simpledemo;

import com.decorator.simpledemo.interfaces.ITraining;

public class Training implements ITraining {
    @Override
    public void train() {
        System.out.println("锻炼：俯卧撑，仰卧起坐。。。");
    }
}
