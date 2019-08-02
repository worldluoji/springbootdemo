package com.decorator.simpledemo;

import com.decorator.simpledemo.interfaces.ITraining;

public abstract class BaseDecorator implements ITraining {
    private ITraining iTraining;
    public BaseDecorator(ITraining iTraining) {
        this.iTraining = iTraining;
    }
    @Override
    public void train() {
        if (iTraining != null) {
            iTraining.train();
        }
    }
}
