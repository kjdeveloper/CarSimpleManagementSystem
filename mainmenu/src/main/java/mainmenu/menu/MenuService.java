package mainmenu.menu;

import persistence.model.Car;
import persistence.model.enums.*;
import service.CarsService;
import service.UserDataService;

import java.math.BigDecimal;
import java.util.*;


public class MenuService {

    private final String filename;
    private CarsService carsService;
    private final UserDataService userDataService;

    public MenuService(String filename) {
        this.filename = filename;
        carsService = new CarsService(filename);
        userDataService = new UserDataService();
    }

    private void menu() {
        System.out.println("\nMENU");
        System.out.println("1. Sorting by numbers of components, engine power or wheel size");
        System.out.println("2. Cars with a specific type of body and price");
        System.out.println("3. Sorting list of cars with specific engine type");
        System.out.println("4. Statistics for price, mileage or engine power");
        System.out.println("5. Map of car = km");
        System.out.println("6. Map of wheel type = list of cars");
        System.out.println("7. List of cars with the specified components");
        System.out.println("8. List of cars");
        System.out.println("----------------------------------------------------");
        System.out.println("0. EXIT");
    }

    public void service() {
        int action;
        do {
            menu();
            action = userDataService.getInt("Choose option: ");
            switch (action) {
                case 1:
                    var sortType = SortType.WHEEL_SIZE;

                    List<Car> carsSortedBy = option1(sortType, true);
                    System.out.println(carsSortedBy);
                    break;

                case 2:
                    var lowerPrice = new BigDecimal(100000);
                    var higherPrice = new BigDecimal(250000);
                    var carBodyType = CarBodyType.COMBI;

                    List<Car> combinatedCars = option2(carBodyType, lowerPrice, higherPrice);
                    System.out.println(combinatedCars);
                    break;

                case 3:
                    List<Car> sortedCars = option3(TypeOfEngine.GASOLINE);
                    System.out.println(sortedCars);
                    break;

                case 4:
                    var statisticsBy = SortTypeForStatistics.MILEAGE;

                    IntSummaryStatistics stat = option4(statisticsBy);
                    System.out.println(
                            " Average value: " + stat.getAverage() +
                            "| Max value: " + stat.getMax() +
                            " | Min value: " + stat.getMin());
                    break;

                case 5:
                    Map<Car, Integer> map = option5();
                    map.forEach((k, v) -> System.out.println(k + " = " + v));
                    break;

                case 6:
                    Map<TypeOfWheel, List<Car>> mapOfCarsWithSpecificWheels = option6();
                    mapOfCarsWithSpecificWheels.forEach((k, v) -> System.out.println(k + " " + v));
                    break;

                case 7:
                    List<String> list = new ArrayList<>(Arrays.asList("ABS", "AIR CONDITIONING"));
                    List<Car> checkComponentsList = option7(list);
                    for (Car car: checkComponentsList) {
                        System.out.println(car);
                    }
                    break;

                case 8:
                    Set<Car> cars = option8();
                    for (Car car : cars) {
                        System.out.println(car);
                    }
                    break;

                case 0:
                    userDataService.close();
                    System.out.println("Bye bye");
                    return;
            }
        } while (true);
    }


    private List<Car> option1(SortType sortType, boolean descending) {
        return carsService.sortBy(sortType, descending);
    }

    private List<Car> option2(CarBodyType carBodyType, BigDecimal lower, BigDecimal higher) {
        if (lower.compareTo(higher) > 0) {
            throw new IllegalArgumentException("PRICES IS NOT CORRECT");
        }

        return carsService.combinationOfCarWithSpecificBodyAndPrice(carBodyType, lower, higher);
    }

    private List<Car> option3(TypeOfEngine typeOfEngine) {
        return carsService.sortedCarsWithSPecificEngine(typeOfEngine);
    }

    private IntSummaryStatistics option4(SortTypeForStatistics sortTypeForStatistics) {
        return carsService.statisticsBy(sortTypeForStatistics);
    }

    private Map<Car, Integer> option5() {
        return carsService.carAndMileage();
    }

    private Map<TypeOfWheel, List<Car>> option6() {
        return carsService.carsWithSpecificTypeOFWheels();
    }

    private List<Car> option7(List<String> comList) {
        return carsService.checkComponentsFromList(comList);
    }

    private Set<Car> option8() {
        return carsService.showAllCars();
    }
}
