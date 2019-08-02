package com.decorator.simpledemo;

import com.decorator.simpledemo.interfaces.ITraining;

public class MyTrainingDemo {
    public static void main(String[] args) {
        ITraining training = new Training();
        FullTrain fullTrain = new FullTrain(training);
        fullTrain.train();
    }
}
