package service;

import exceptions.MyExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.model.enums.*;
import persistence.repository.impl.JsonCarsRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarServiceTest {

    private JsonCarsRepository jsonCarsRepository = new JsonCarsRepository();
    private DataGeneratorForCarEquipment dataGeneratorForCarEquipment = new DataGeneratorForCarEquipment();

    public void generateFileForTest(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";
        jsonCarsRepository.saveAll(jsonFilename, dataGeneratorForCarEquipment.generateCarsSet());
    }

    @Test
    @DisplayName("Sorting by components quantity descending")
    public void test1() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.COMPONENTS_SIZE, false);

        assertEquals("AUDI", cars.get(0).getModel(), "TEST 1 FAILED");

    }

    @Test
    @DisplayName("Sorting by components quantity ascending")
    public void test2() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.COMPONENTS_SIZE, true);

        assertEquals("SKODA", cars.get(0).getModel(), "TEST 2 FAILED");

    }

    @Test
    @DisplayName("Sorting by engine power descending")
    public void test3() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.ENGINE_POWER, false);

        assertEquals("FIAT", cars.get(0).getModel(), "TEST 3 FAILED");

    }

    @Test
    @DisplayName("Sorting by engine power ascending")
    public void test4() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.ENGINE_POWER, true);

        assertEquals("BMW", cars.get(0).getModel(), "TEST 4 FAILED");

    }

    @Test
    @DisplayName("Sorting by wheel size descending")
    public void test5() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.WHEEL_SIZE, false);

        assertEquals("MAZDA", cars.get(0).getModel(), "TEST 5 FAILED");

    }

    @Test
    @DisplayName("Sorting by wheel size ascending")
    public void test6() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).sortBy(SortType.WHEEL_SIZE, true);

        assertEquals("AUDI", cars.get(0).getModel(), "TEST 6 FAILED");
    }

    @Test
    @DisplayName("Combination of cars with specific car body type and price (COMBI 50000 - 200000)")
    public void test7() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var carBodyCar = CarBodyType.COMBI;
        var lowerPrice = new BigDecimal(50000);
        var higherPrice = new BigDecimal(200000);

        var cars = new CarsService(jsonFilename).combinationOfCarWithSpecificBodyAndPrice(carBodyCar, lowerPrice, higherPrice);

        assertEquals(1, cars.size(), "TEST 7 FAILED");
    }

    @Test
    @DisplayName("Combination of cars with specific car body type and price (HATCHBACK 200000-500000)")
    public void test8() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var carBodyCar = CarBodyType.HATCHBACK;
        var lowerPrice = new BigDecimal(200000);
        var higherPrice = new BigDecimal(500000);

        var cars = new CarsService(jsonFilename).combinationOfCarWithSpecificBodyAndPrice(carBodyCar, lowerPrice, higherPrice);

        assertEquals(0, cars.size(), "TEST 8 FAILED");

    }

    @Test
    @DisplayName("Combination of cars exceptions")
    public void test9() {
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var carBodyCar = CarBodyType.SEDAN;
        var lowerPrice = new BigDecimal(30000);
        var higherPrice = new BigDecimal(BigInteger.ZERO);

        Throwable throwable = Assertions.assertThrows(
                MyExceptions.class,
                () -> new CarsService(jsonFilename).combinationOfCarWithSpecificBodyAndPrice(carBodyCar, lowerPrice, higherPrice)
        );

        assertEquals("HIGHER PRICE IS NULL OR IS EQUAL TO 0", throwable.getMessage(), "TEST 9 FAILED");
    }

    @Test
    @DisplayName("Sorted cars with specific engine (DIESEL)")
    public void test10(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var typeOfEngine = TypeOfEngine.DIESEL;

        var cars = new CarsService(jsonFilename).sortedCarsWithSPecificEngine(typeOfEngine);

        assertEquals(2, cars.size(),"TEST 10 FAILER");
    }

    @Test
    @DisplayName("Sorted cars with specific engine (GASOLINE)")
    public void test11(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var typeOfEngine = TypeOfEngine.GASOLINE;

        var cars = new CarsService(jsonFilename).sortedCarsWithSPecificEngine(typeOfEngine);

        assertEquals(3, cars.size(), "TEST 11 FAILED");
    }

    @Test
    @DisplayName("Statistics by basic price")
    public void test12(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var sortType = SortTypeForStatistics.BASIC_PRICE;

        var cars = new CarsService(jsonFilename).statisticsBy(sortType);

        assertEquals(172400, cars.getAverage(),"TEST 12 FAILED");
    }

    @Test
    @DisplayName("Statistics by basic mileage")
    public void test13(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var sortType = SortTypeForStatistics.MILEAGE;

        var cars = new CarsService(jsonFilename).statisticsBy(sortType);

        assertEquals(75, cars.getAverage(),"TEST 13 FAILED");
    }

    @Test
    @DisplayName("Statistics by basic engine power")
    public void test14(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var sortType = SortTypeForStatistics.ENGINE_POWER;

        var cars = new CarsService(jsonFilename).statisticsBy(sortType);

        assertEquals(2, cars.getAverage(),"TEST 14 FAILED");
    }

    @Test
    @DisplayName("Car and mileage map")
    public void test15(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).carAndMileage();

        assertEquals(6, cars.entrySet().size(),"TEST !% FAILED");
    }

    @Test
    @DisplayName("Map with specific type of wheel (SUMMER OR WINTER)")
    public void test16(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).carsWithSpecificTypeOFWheels();

        assertEquals(2, cars.keySet().size(), "TEST 16 FAILED");
    }

    @Test
    @DisplayName("Summer type of wheel values size")
    public void test17(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).carsWithSpecificTypeOFWheels();

        assertEquals(4, cars.get(TypeOfWheel.SUMMER).size(), "TEST 17 FAILED");
    }

    @Test
    @DisplayName("Winter type of wheel values size")
    public void test18(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";
        var cars = new CarsService(jsonFilename).carsWithSpecificTypeOFWheels();

        assertEquals(2, cars.get(TypeOfWheel.WINTER).size(), "TEST 18 FAILED");
    }

    @Test
    @DisplayName("Cars list with components in argument")
    public void test19(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        List<String> components = List.of("ABS", "AIR CONDITIONING");

        var cars = new CarsService(jsonFilename).checkComponentsFromList(components);

        assertEquals(3, cars.size(), "TEST 19 FAILED");
    }

    @Test
    @DisplayName("Exception when component list are 0")
    public void test20(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        List<String> components = List.of();

        Throwable throwable = Assertions.assertThrows(
                MyExceptions.class,
                () -> new CarsService(jsonFilename).checkComponentsFromList(components));

        assertEquals( "COMPONENT LIST CANNOT BE 0", throwable.getMessage(), "TEST 20 FAILED");
    }

    @Test
    @DisplayName("Find all cars")
    public void test21(){
        String jsonFilename = "C:\\Programowanie\\carEquipmentStatisticsFinal\\service\\src\\test\\java\\service\\resourcesForTest\\carsForTest.json";

        var cars = new CarsService(jsonFilename).showAllCars();

        assertEquals(6, cars.size(), "TEST 21 FAILED");
    }


}
