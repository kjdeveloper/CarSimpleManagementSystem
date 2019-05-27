package persistence.repository.impl;

import persistence.model.Car;
import persistence.model.Cars;
import persistence.repository.CarsRepository;
import persistence.repository.converters.CarsConverter;

import java.util.Collections;
import java.util.Set;

public class JsonCarsRepository implements CarsRepository {

    @Override
    public void saveAll(Set<Car> cars) {
        final String jsonFilename = "C:\\Programowanie\\CarEquipmentStatistics\\persistence\\src\\main\\java\\resources\\CarsSetForCarEquipments.json";
        CarsConverter carsConverter = new CarsConverter(jsonFilename);
        Cars cars1 = new Cars(cars);
        carsConverter.toJson(cars1);
    }

    @Override
    public Set<Car> findAll(String jsonFilename) {
        return new CarsConverter(jsonFilename).fromJson()
                .map(Cars::getCars)
                .orElse(Collections.emptySet());
    }

    public void saveAll(String jsonFilename, Set<Car> cars) {
        CarsConverter carsConverter = new CarsConverter(jsonFilename);
        Cars cars1 = new Cars(cars);
        carsConverter.toJson(cars1);
    }
}
