package ReflectDemo.refconstrutor.demo;

import ReflectDemo.model.User;

public class ConstrutorDemo {
    public static void main(String[] args) {
        try {
            Class<?> userClass = Class.forName("ReflectDemo.model.User");
            User user1 = (User)userClass.newInstance();
            System.out.println("反射实例化无参对象" + user1);
            User user2 = (User)userClass.getConstructor(new Class[]{String.class, int.class, short.class}).newInstance(new Object[]{"高圆圆", 18, (short)1});
            System.out.println("反射实例化带参对象" + user2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
