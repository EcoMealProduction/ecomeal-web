package model.cart;

public class NotEnoughProductsInStockException extends Exception {

    private final int productsInStock;

    public NotEnoughProductsInStockException(String message, int productsInStock) {
        super(message);
        this.productsInStock = productsInStock;
    }

    public int productsInStock() {
        return productsInStock;
    }
}
