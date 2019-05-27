package persistence.model;


import persistence.model.enums.TypeOfWheel;

public class Wheel {

    private String model;
    private int size;
    private TypeOfWheel typeOfWheel;

    public Wheel() {
    }

    public Wheel(String model, int size, TypeOfWheel typeOfWheel) {
        setModel(model);
        setSize(size);
        this.typeOfWheel = typeOfWheel;
    }

    public String getModel() {
        return model;
    }

    public int getSize() {
        return size;
    }

    public TypeOfWheel getTypeOfWheel() {
        return typeOfWheel;
    }

    public void setTypeOfWheel(TypeOfWheel typeOfWheel) {
        this.typeOfWheel = typeOfWheel;
    }

    private static final String MODEL_REGEX = "[A-Z ]+";



    public void setModel(String model) {
        if (model.matches(MODEL_REGEX)) {
            this.model = model;
        } else {
            System.err.println("Invalid wheel model");
        }
    }

    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        } else {
            System.err.println("Invalid wheel size");
        }
    }

    @Override
    public String toString() {
        return "Wheel: "
                + model +
                "| size: " + size +
                "| type: " + typeOfWheel;
    }
}
