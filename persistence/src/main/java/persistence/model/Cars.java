package persistence.model;

import java.util.Objects;
import java.util.Set;


public class Cars {

    private Set<Car> cars;

    public Cars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public Cars() {
    }

    @Override
    public String toString() {
        return "[ Cars:" + cars +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}

