package LambdaDemo;

import java.util.Arrays;

public class TestLambda {
    public static void main(String[] args) {
        /*
        * 筛选(filter)出忽略大小写=foo的元素，全部转化(map)为大写，遍历（forEach）的时候进行打印操作
        * */
        Arrays.asList("Foo", "Bar").stream()
                .filter(s -> s.equalsIgnoreCase("foo"))
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("******************************************");

        /**
         * Stream的map和flatMap的区别:
         * map会将一个元素变成一个新的Stream
         * 但是flatMap会将结果打平，得到一个单个元素
         */
        Arrays.stream(new String[]{"s1", "s2", "s3"})
                .map(s -> Arrays.asList(s))
                .flatMap(l -> l.stream())
                .forEach(System.out::println);
    }
}
