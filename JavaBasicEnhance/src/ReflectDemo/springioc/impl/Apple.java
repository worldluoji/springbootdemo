package ReflectDemo.springioc.impl;

import ReflectDemo.springioc.interfaces.fruit;

public class Apple implements fruit {
    @Override
    public void eat() {
        System.out.println("eating apple");
    }
}
