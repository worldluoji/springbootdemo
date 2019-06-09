package ReflectDemo.springioc.impl;

import ReflectDemo.springioc.interfaces.fruit;

public class Orange implements fruit {
    @Override
    public void eat() {
        System.out.println("eating orange");
    }
}
