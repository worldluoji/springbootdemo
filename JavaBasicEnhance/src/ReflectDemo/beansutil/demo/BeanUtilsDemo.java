package ReflectDemo.beansutil.demo;

import ReflectDemo.model.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtilsDemo {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("luoji");
        user1.setAge(28);
        user1.setGender((short)0);
        user1.setHobbies(Arrays.asList("piano","running"));
        System.out.println(user1);
        User user2 = new User();
        try {
            BeanUtils.copyProperties(user2, user1);
            System.out.println(user2);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
