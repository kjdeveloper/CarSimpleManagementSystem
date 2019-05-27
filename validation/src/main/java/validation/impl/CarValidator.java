package validation.impl;


import persistence.model.Car;
import validation.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarValidator implements Validator<Car> {

    private Map<String, String> errors = new HashMap<>();

    @Override
    public Map<String, String> validate(Car car) {
        errors.clear();

        if (car == null){
            errors.put("car", "null");
        }
        if (!isModelValid(car)){
            errors.put("model", "model is not valid " + car.getModel());
        }
        if (!isBasicPriceValid(car)){
            errors.put("basic price", "basic price is not valid " + car.getBasicPrice());
        }
        if (!isMileageValid(car)){
            errors.put("mileage", "mileage is not valid " + car.getMileage());
        }

        return errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private boolean isModelValid(Car car) {
        String MODEL_NAME_REGEX = "[A-Z ]+";
        return car.getModel().matches(MODEL_NAME_REGEX);
    }

    private boolean isBasicPriceValid(Car car) {
        return car.getBasicPrice().compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isMileageValid(Car car) {
        return car.getMileage() >= 0;
    }
}
