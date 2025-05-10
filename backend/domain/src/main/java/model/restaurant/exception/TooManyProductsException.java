package model.restaurant.exception;

public class TooManyProductsException extends RuntimeException {
    public TooManyProductsException(String message) {
        super(message);
    }
}
