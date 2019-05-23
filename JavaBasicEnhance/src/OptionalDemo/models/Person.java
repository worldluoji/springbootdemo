package OptionalDemo.models;

import javax.swing.event.CaretListener;
import java.util.Optional;

public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }

    public Optional<Person> getPersonAsOptional() {
        return Optional.ofNullable(this);
    }
}
