package persistence.repository.converters;

import persistence.model.Cars;

public class CarsConverter extends JsonConverter<Cars> {
    public CarsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
