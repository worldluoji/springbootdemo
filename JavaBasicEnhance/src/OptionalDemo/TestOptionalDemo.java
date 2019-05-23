package OptionalDemo;

import OptionalDemo.models.Car;
import OptionalDemo.models.Insurance;
import OptionalDemo.models.Person;

import java.util.Optional;

/**
*  使用Optional包装以后不用再嵌套判空，代码更优雅
 *  flatMap使返回值就是实际是什么类型就返回什么类型
 *  把Optional看成只有一个元素的stream
* */
public class TestOptionalDemo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setCar(Optional.of(new Car("dazhong", Optional.of(new Insurance("pingan")))));
        //有车有保险
        String insuranceName = p.getPersonAsOptional().flatMap(Person::getCar)
                                .flatMap(Car::getInsurance)
                                .map(Insurance::getName)
                                .orElse("unknown");
        System.out.println(insuranceName);

        //有车但是没保险
        p.setCar(Optional.of(new Car("dazhong", Optional.empty())));
        insuranceName = p.getPersonAsOptional().flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
        System.out.println(insuranceName);

        //无车
        p.setCar(Optional.empty());
        insuranceName = p.getPersonAsOptional().flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
        System.out.println(insuranceName);
    }
}
