package LambdaDemo.apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PredicateApple {
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result= new ArrayList<>();
        for(Apple apple : inventory) {
            if (p.select(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(50, "yellow", 1.0),
                new Apple(100, "green", 3.0),
                new Apple(150, "red", 5.0)
        );

        System.out.println("查找重量大于80g的苹果:");
        List<Apple> results = filterApples(apples, (Apple a) -> a.getWeight() > 80);
        results.forEach(r -> System.out.println(r) );
        System.out.println("***************************");
        System.out.println("查找红苹果:");
        results = filterApples(apples, (Apple a) -> a.getColor().equalsIgnoreCase("RED"));
        results.forEach(r -> System.out.println(r) );

    }

}
