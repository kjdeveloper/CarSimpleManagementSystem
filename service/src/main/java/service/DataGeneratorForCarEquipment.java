package service;

import persistence.model.Car;
import persistence.model.CarBody;
import persistence.model.Engine;
import persistence.model.Wheel;
import persistence.model.enums.CarBodyColor;
import persistence.model.enums.CarBodyType;
import persistence.model.enums.TypeOfEngine;
import persistence.model.enums.TypeOfWheel;
import persistence.repository.impl.JsonCarsRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DataGeneratorForCarEquipment {

    private final Set<Car> carSet;

    public DataGeneratorForCarEquipment() {
        this.carSet = generateCarsSet();
    }

    public Set<Car> getCarSet() {
        return carSet;
    }

    public Set<Car> generateCarsSet(){
        return new HashSet<>(Arrays.asList(
                new Car.CarBuilder("HONDA", new BigDecimal(240000), 20,
                        new Engine(TypeOfEngine.LPG, 3.0),
                        new CarBody(CarBodyType.COMBI, CarBodyColor.WHITE, new ArrayList<>(Arrays.asList("ABS", "SUNROOF", "AIR CONDITIONING"))),
                        new Wheel("DEBICA", 21, TypeOfWheel.SUMMER)).build(),
                new Car.CarBuilder("BMW", new BigDecimal(444000), 60,
                        new Engine(TypeOfEngine.DIESEL, 3.2),
                        new CarBody(CarBodyType.COMBI, CarBodyColor.BLACK, new ArrayList<>(Arrays.asList("BLUETOOTH", "ALLOY WHEELS"))),
                        new Wheel("CONTINENTAL", 23, TypeOfWheel.WINTER)).build(),
                new Car.CarBuilder("MAZDA", new BigDecimal(140000), 230,
                        new Engine(TypeOfEngine.GASOLINE, 2.0),
                        new CarBody(CarBodyType.HATCHBACK, CarBodyColor.RED, new ArrayList<>(Arrays.asList("PIONEER SOUND SYSTEM", "AIR CONDITIONING"))),
                        new Wheel("DEBICA COOL", 15, TypeOfWheel.SUMMER)).build(),
                new Car.CarBuilder("AUDI", new BigDecimal(140400), 220,
                        new Engine(TypeOfEngine.DIESEL, 1.6),
                        new CarBody(CarBodyType.COMBI, CarBodyColor.BLACK, new ArrayList<>(Arrays.asList("ELECTRONIC MIRRORS", "SUNROOF"))),
                        new Wheel("DRIFT", 215, TypeOfWheel.WINTER)).build(),
                new Car.CarBuilder("SKODA", new BigDecimal(50000), 120,
                        new Engine(TypeOfEngine.GASOLINE, 2.0),
                        new CarBody(CarBodyType.HATCHBACK, CarBodyColor.GREEN, new ArrayList<>(Arrays.asList("ABS", "AIR CONDITIONING", "ELECTORNIC MIRRORS", "BLUETOOTH"))),
                        new Wheel("RACE", 19, TypeOfWheel.SUMMER)).build(),
                new Car.CarBuilder("FIAT", new BigDecimal(20000), 11120,
                        new Engine(TypeOfEngine.GASOLINE, 1.0),
                        new CarBody(CarBodyType.SEDAN, CarBodyColor.BLACK, new ArrayList<>(Arrays.asList("ABS", "AIR CONDITIONING", "ELECTORNIC MIRRORS", "BLUETOOTH"))),
                        new Wheel("RACE", 15, TypeOfWheel.SUMMER)).build()
        ));
    }

    public void saveToFile(Set<Car> cars){
        JsonCarsRepository jsonCarsRepository = new JsonCarsRepository();
        jsonCarsRepository.saveAll(cars);
    }
}
