package ReflectDemo.refMethod.demo;

import ReflectDemo.model.User;

import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args) {
        try {
            Class<?> userClass = Class.forName("ReflectDemo.model.User");
            // 获取当前类和所有超类的public method
            System.out.println("******************************");
            Method[] methods = userClass.getMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }

            // 获取当前类声明的所有的method,包括private
            System.out.println("******************************");
            Method[] declaredMethods = userClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println(method.getName());
            }
            System.out.println("******************************");

            // 获取并调用私有方法,不能使用getMethod. 前提时要知道方法名称
            User user = new User();
            Method setAddressMethod = userClass.getDeclaredMethod("setAddress", new Class[]{String.class});
            setAddressMethod.setAccessible(true);// 反射对象使用时取消Java语言访问检查
            setAddressMethod.invoke(user,"四川省成都市西源大道1899号");
            Method getAddressMethod = userClass.getDeclaredMethod("getAddress");
            getAddressMethod.setAccessible(true);
            String address = (String)getAddressMethod.invoke(user);
            System.out.println(address);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
