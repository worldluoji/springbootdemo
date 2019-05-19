package LambdaDemo.apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestSortApple {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(50, "yellow", 3.0),
                new Apple(150, "green", 2.0),
                new Apple(100, "red", 5.0)
        );
        apples.forEach(apple -> System.out.println(apple));
        System.out.println("**********************************");
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("按照重量排序后的结果为：");
        apples.forEach(apple -> System.out.println(apple));
        System.out.println("**********************************");
        apples.sort(Comparator.comparing(Apple::getPrice));
        System.out.println("按照价格排序后的结果为：");
        apples.forEach(apple -> System.out.println(apple));
        System.out.println("**********************************");
        apples.sort((Apple a, Apple b) -> b.getWeight() - a.getWeight());
        System.out.println("按照重量逆序排序后的结果为：");
        apples.forEach(apple -> System.out.println(apple));

    }
}
