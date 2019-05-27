package persistence.repository;


import persistence.model.Car;

import java.util.Set;

public interface CarsRepository {

    void saveAll(Set<Car> cars);

    Set<Car> findAll(String jsonFilename);

}
