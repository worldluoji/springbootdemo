package ReflectDemo.springioc;


import ReflectDemo.springioc.interfaces.fruit;

import java.io.IOException;
import java.util.Properties;

public class SpringIocDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = InitProperties.getProperties();
        fruit f = MySpingFactory.getInstance(properties.getProperty("apple"));
        if (f != null) {
            f.eat();
        }
    }
}
