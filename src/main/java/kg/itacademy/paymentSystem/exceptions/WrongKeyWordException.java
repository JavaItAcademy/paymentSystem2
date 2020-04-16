package kg.itacademy.paymentSystem.exceptions;

public class WrongKeyWordException extends Exception {
    private static final String message = "Wrong key word";

    public WrongKeyWordException() {
        super(message);
    }
}
