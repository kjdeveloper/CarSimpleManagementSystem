package persistence.model;

import java.math.BigDecimal;

public class Car {

    private String model;
    private BigDecimal basicPrice;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;

    public Car() {
    }

    public Car(String model, BigDecimal basicPrice, int mileage, Engine engine, CarBody carBody, Wheel wheel) {
        this.model = model;
        this.basicPrice = basicPrice;
        this.mileage = mileage;
        this.engine = engine;
        this.carBody = carBody;
        this.wheel = wheel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Car(CarBuilder carBuilder) {
        this.model = carBuilder.model;
        this.basicPrice = carBuilder.basicPrice;
        this.mileage = carBuilder.mileage;
        this.engine = carBuilder.engine;
        this.carBody = carBuilder.carBody;
        this.wheel = carBuilder.wheel;
    }

    @Override
    public String toString() {
        return "[ model: " + model +
                "| basic price: " + basicPrice +
                "| mileage: " + mileage +
                "| " + engine +
                "| " + carBody +
                "| " + wheel +
                ']';
    }


    public static class CarBuilder {

        private String model;
        private BigDecimal basicPrice;
        private int mileage;
        private Engine engine;
        private CarBody carBody;
        private Wheel wheel;

        public String getModel() {
            return model;
        }

        public BigDecimal getBasicPrice() {
            return basicPrice;
        }

        public int getMileage() {
            return mileage;
        }

        public Engine getEngine() {
            return engine;
        }

        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public CarBody getCarBody() {
            return carBody;
        }

        public void setCarBody(CarBody carBody) {
            this.carBody = carBody;
        }

        public Wheel getWheel() {
            return wheel;
        }

        public void setWheel(Wheel wheel) {
            this.wheel = wheel;
        }



        public CarBuilder(String model, BigDecimal basicPrice, int mileage, Engine engine, CarBody carBody, Wheel wheel) {
            this.model = model;
            this.basicPrice = basicPrice;
            this.mileage = mileage;
            this.engine = engine;
            this.carBody = carBody;
            this.wheel = wheel;
        }

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder basicPrice(BigDecimal basicPrice) {
            this.basicPrice = basicPrice;
            return this;
        }

        public CarBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }


}
