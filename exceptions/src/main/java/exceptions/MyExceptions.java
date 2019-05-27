package exceptions;

public class MyExceptions extends RuntimeException {

    private String message;

    public MyExceptions(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
