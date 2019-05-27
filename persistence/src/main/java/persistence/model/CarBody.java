package persistence.model;


import persistence.model.enums.CarBodyColor;
import persistence.model.enums.CarBodyType;

import java.util.List;

public class CarBody {

    private CarBodyType carBodyType;
    private CarBodyColor carBodyColor;
    private List<String> components;

    public CarBody() {
    }

    public CarBodyType getCarBodyType() {
        return carBodyType;
    }

    public CarBody(CarBodyType carBodyType, CarBodyColor carBodyColor, List<String> components) {
        this.carBodyType = carBodyType;
        this.carBodyColor = carBodyColor;
        this.components = components;
    }

    public void setCarBodyType(CarBodyType carBodyType) {
        this.carBodyType = carBodyType;
    }

    public CarBodyColor getCarBodyColor() {
        return carBodyColor;
    }

    public void setCarBodyColor(CarBodyColor carBodyColor) {
        this.carBodyColor = carBodyColor;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "CarBody: " + carBodyType +
                "| colour: " + carBodyColor +
                "| extraEquipment: " + components;
    }
}
