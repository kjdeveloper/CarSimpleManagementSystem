package persistence.model;


import persistence.model.enums.TypeOfEngine;

public class Engine {

    private TypeOfEngine typeOfEngine;
    private Double power;

    public Engine() {
    }

    public Engine(TypeOfEngine typeOfEngine, Double power) {
        this.typeOfEngine = typeOfEngine;
        setPower(power);
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(TypeOfEngine typeOfEngine) {
        this.typeOfEngine = typeOfEngine;
    }

    public Double getPower() {
        return power;
    }



    public void setPower(Double power) {
        if (power >= 0.0) {
            this.power = power;
        } else {
            System.err.println("Invalid power");
        }
    }

    @Override
    public String toString() {
        return "Engine: " +
                typeOfEngine +
                "| capacity: " + power;
    }

}
