package OptionalDemo.models;

import java.util.Optional;

public class Car {
    private String carName;
    private Optional<Insurance> insurance;

    public Car(String carName, Optional<Insurance> insurance) {
        this.carName = carName;
        this.insurance = insurance;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
