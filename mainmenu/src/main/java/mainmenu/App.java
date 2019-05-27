package mainmenu;

import mainmenu.menu.MenuService;

public class App {
    public static void main(String[] args) {

        /*DataGeneratorForCarEquipment dataGeneratorForCarEquipment = new DataGeneratorForCarEquipment();
        Set<Car> carSet = dataGeneratorForCarEquipment.getCarSet();
        dataGeneratorForCarEquipment.saveToFile(carSet);*/

        new MenuService("C:\\Programowanie\\CarEquipmentStatisticsFinal\\persistence\\src\\main\\java\\resources\\CarsSetForCarEquipments.json").service();

    }
}
