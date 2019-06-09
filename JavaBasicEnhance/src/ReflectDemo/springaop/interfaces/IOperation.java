package ReflectDemo.springaop.interfaces;

import java.lang.reflect.Method;

public interface IOperation {
    void beforeMethod(Method method);
    void afterMethod(Method method);
}
