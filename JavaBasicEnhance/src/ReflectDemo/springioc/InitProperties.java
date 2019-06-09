package ReflectDemo.springioc;

import java.io.*;
import java.util.Properties;

public class InitProperties {
    public static Properties getProperties() throws FileNotFoundException, IOException {
        Properties properties =  new Properties();
        File f = new File("fruit.properties");
        if (f.exists()) {
            properties.load(new FileReader(f));
        } else {
            properties.setProperty("apple", "ReflectDemo.springioc.impl.Apple");
            properties.setProperty("orange", "ReflectDemo.springioc.impl.Orange");
            properties.store(new FileOutputStream(f),"FRUIT CLASS");
        }
        return properties;
    }
}
