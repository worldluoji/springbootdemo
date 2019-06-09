package ReflectDemo.springaop;

import ReflectDemo.springaop.impl.Hello;
import ReflectDemo.springaop.impl.Operation;
import ReflectDemo.springaop.interfaces.IHello;

public class SpringAopDemo {
    public static void main(String[] args) {
        IHello hello = (IHello)new DynaProxyHello().bind(new Hello(), new Operation());
        hello.sayHello("luoji");
        hello.sayGoodbye("luoji");
    }
}
