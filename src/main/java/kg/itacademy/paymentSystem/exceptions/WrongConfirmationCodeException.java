package kg.itacademy.paymentSystem.exceptions;

public class WrongConfirmationCodeException extends Exception {
    private static final String message = "Wrong confirmation code";

    public WrongConfirmationCodeException() {
        super(message);
    }
}
