package ReflectDemo.refField.demo;

import ReflectDemo.model.User;

import java.lang.reflect.Field;

public class FieldDemo {
    public static void main(String[] args) {
        try {
            Class<?> userClass = Class.forName("ReflectDemo.model.User");

            // 获取该类和其超类所有的public fields
            Field[] fields = userClass.getFields();
            System.out.println("******************************");
            for (Field field:fields ) {
                System.out.println(field);
            }

            // 获取该类自己声明的所有fields
            System.out.println("******************************");
            Field[] declaredFields = userClass.getDeclaredFields();
            for (Field field:declaredFields ) {
                System.out.println(field);
            }
            System.out.println("******************************");
            Field nameField = userClass.getDeclaredField("name");
            User user = new User();
            nameField.setAccessible(true);
            nameField.set(user, "luoji");//通过set方法设置name属性
            String name = (String)nameField.get(user); //通过get方法获取对象中的name属性
            System.out.println(name);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
