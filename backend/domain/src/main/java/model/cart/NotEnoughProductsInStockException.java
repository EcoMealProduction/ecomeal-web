package model.cart;

public class NotEnoughProductsInStockException extends RuntimeException {
    public NotEnoughProductsInStockException(String message) {
        super(message);
    }
}
