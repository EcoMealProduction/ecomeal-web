package model.cart;

import model.restaurant.Product;

public record CartItem(Product product, int quantity) {

    public CartItem {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");
    }
}
