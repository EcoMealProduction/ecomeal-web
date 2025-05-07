package model.restaurant;

/**
 * Custom unchecked exception thrown when an operation involves an invalid product totalQuantity,
 * such as negative values or quantities exceeding allowed limits.
 */
public class InvalidQuantityException extends RuntimeException {
    /**
     * Constructs a new InvalidQuantityException with a specified detail message.
     *
     * @param message the detail message explaining the validation failure
     */
    public InvalidQuantityException(String message) {
        super(message);
    }
}
