package ReflectDemo.springaop.impl;

import ReflectDemo.springaop.Logger;
import ReflectDemo.springaop.constants.LogLevel;
import ReflectDemo.springaop.interfaces.IOperation;

import java.lang.reflect.Method;

public class Operation implements IOperation {
    @Override
    public void beforeMethod(Method method) {
        Logger.logging(LogLevel.INFO, method.getName() + " method start");
    }

    @Override
    public void afterMethod(Method method) {
        Logger.logging(LogLevel.INFO, method.getName() + " method end");
    }
}
