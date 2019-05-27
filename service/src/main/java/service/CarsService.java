package service;

import exceptions.MyExceptions;
import persistence.model.Car;
import persistence.model.enums.*;
import persistence.repository.impl.JsonCarsRepository;
import validation.impl.CarValidator;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CarsService {

    private Set<Car> cars;

    public CarsService() {
    }

    public CarsService(String filename) {
        var atomicInteger = new AtomicInteger(1);
        var carValidator = new CarValidator();

        cars = new JsonCarsRepository()
                .findAll(filename)
                .stream()
                .filter(car -> {
                    Map<String, String> errors = carValidator.validate(car);

                    if (carValidator.hasErrors()) {
                        System.out.println("ORDER NO: " + atomicInteger.get());
                        System.out.println("----------- VALIDATION ERRORS -----------");
                        errors.forEach((k, v) -> System.out.println(k + " -> " + v));
                        System.out.println("-------------------------------------------");
                    }

                    atomicInteger.incrementAndGet();

                    return !carValidator.hasErrors();
                }).collect(Collectors.toSet());
    }

    //1
    public List<Car> sortBy(SortType sortType, boolean descending) {
        Stream<Car> carStream = null;
        switch (sortType) {
            case COMPONENTS_SIZE:
                carStream = cars.stream().sorted(Comparator.comparing(c -> c.getCarBody().getComponents().size()));
                break;
            case ENGINE_POWER:
                carStream = cars.stream().sorted(Comparator.comparing(p -> p.getEngine().getPower()));
                break;
            case WHEEL_SIZE:
                carStream = cars.stream().sorted(Comparator.comparing(w -> w.getWheel().getSize()));
                break;
        }

        List<Car> sortedCars = carStream.collect(Collectors.toList());

        if (descending) {
            Collections.reverse(sortedCars);
        }
        return sortedCars;
    }

    //2
    public List<Car> combinationOfCarWithSpecificBodyAndPrice(CarBodyType carBodyType, final BigDecimal lowerPrice, final BigDecimal higherPrice) {
        if (carBodyType == null) {
            throw new MyExceptions("CARBODYTYPE IS NULL");
        }

        if (lowerPrice == null) {
            throw new MyExceptions("LOWER PRICE IS NULL");
        }

        if (higherPrice == null || higherPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MyExceptions("HIGHER PRICE IS NULL OR IS EQUAL TO 0");
        }

        if (lowerPrice.compareTo(higherPrice) > 0) {
            throw new MyExceptions("LOWER PRICE IS BIGGER THAN HIGHER");
        }

        return cars.stream()
                .filter(body -> body.getCarBody().getCarBodyType().equals(carBodyType))
                .filter(p -> p.getBasicPrice().compareTo(lowerPrice) >= 0 && p.getBasicPrice().compareTo(higherPrice) <= 0)
                .collect(Collectors.toList());
    }

    //3
    public List<Car> sortedCarsWithSPecificEngine(TypeOfEngine typeOfEngine) {

        return cars.stream()
                .filter(e -> e.getEngine().getTypeOfEngine().equals(typeOfEngine))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    //4
    public IntSummaryStatistics statisticsBy(SortTypeForStatistics sortTypeForStatistics) {
       IntSummaryStatistics carStream = null;

        switch (sortTypeForStatistics) {
            case BASIC_PRICE:
                carStream = cars.stream().collect(Collectors.summarizingInt(p -> p.getBasicPrice().intValue()));
                break;
            case MILEAGE:
                carStream = cars.stream().collect(Collectors.summarizingInt(Car::getMileage));
                break;
            case ENGINE_POWER:
                carStream = cars.stream().collect(Collectors.summarizingInt(e -> e.getEngine().getPower().intValue()));
                break;
        }

        return carStream;
    }


    //5
    public Map<Car, Integer> carAndMileage() {
        return cars.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        Car::getMileage,
                        (v1, v2) -> v1 + v2,
                        LinkedHashMap::new
                ));
    }

    //6
    public Map<TypeOfWheel, List<Car>> carsWithSpecificTypeOFWheels() {
        return cars.stream()
                .collect(Collectors.groupingBy(x -> x.getWheel().getTypeOfWheel()));
    }

    //7
    public List<Car> checkComponentsFromList(List<String> components) {
        if (components.size() == 0) {
            throw new MyExceptions("COMPONENT LIST CANNOT BE 0");
        }

        return cars.stream()
                .filter(c -> c.getCarBody().getComponents().containsAll(components))
                .collect(Collectors.toList());
    }

    //8
    public Set<Car> showAllCars() {
        return cars;
    }
}
