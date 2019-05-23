package OptionalDemo.models;

import java.util.Optional;

/**
* Optional中有isPresent方法判空
 * 可以用map方法返回一个Optional包裹对应属性
* */
public class TestOptionalDemo2 {
    public static void main(String[] args) {
        Optional<Car> car = Optional.of(new Car("dazhong", Optional.empty()));
        Optional<String> carName = car.map(Car::getCarName);
        if (carName.isPresent()) {
            System.out.println(carName.get());
        }

        car = Optional.empty();
        carName = car.map(Car::getCarName);
        if (carName.isPresent()) {
            System.out.println(carName.get());
        }
    }
}
