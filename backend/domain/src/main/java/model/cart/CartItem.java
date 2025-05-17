package model.cart;

import model.restaurant.Product;

/**
 * Represents a single item in a client's shopping cart.
 * Maintains a reference to the associated product and the reserved quantity.
 * Enforces quantity rules and ensures product reservations stay in sync.
 */
public record CartItem(Product product, int quantity) {

    /**
     * Constructs a CartItem and validates initial quantity constraints.
     * Ensures that the requested quantity does not exceed total product stock.
     *
     * @throws IllegalArgumentException if quantity is non-positive
     * @throws NotEnoughProductsInStockException if requested quantity exceeds available stock
     */
    public CartItem {
        if (quantity < product.reservedQuantity())
            throw new IllegalArgumentException("Given quantity mismatches the product reserved quantity.");

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");

        if (quantity > product.totalQuantity())
            throw new NotEnoughProductsInStockException("Not enough products in stock.");
    }

    /**
     * Returns a new CartItem with increased quantity and updated product reservation.
     *
     * @param additionalQuantity number of units to add
     * @return a new CartItem with updated state
     */
    public CartItem increaseQuantity(int additionalQuantity) {
        if (additionalQuantity <= 0) throw new IllegalArgumentException("Quantity to add must be positive.");

        final Product updatedProduct = product.reserve(additionalQuantity);
        final int newQuantity = quantity + additionalQuantity;

        return new CartItem(updatedProduct, newQuantity);
    }

    /**
     * Returns a new CartItem with decreased quantity and updated product release.
     * If resulting quantity is 0, caller should remove the item from the cart.
     *
     * @param quantityToRemove number of units to remove
     * @return a new CartItem with updated state
     */
    public CartItem decreaseQuantity(int quantityToRemove) {
        if (quantityToRemove <= 0) throw new IllegalArgumentException("Quantity to remove must be positive.");
        if (quantityToRemove > quantity) throw new IllegalArgumentException("Cannot remove more than current quantity.");

        final Product updatedProduct = product.reduce(quantityToRemove);
        final int newQuantity = quantity - quantityToRemove;

        return new CartItem(updatedProduct, newQuantity);
    }

}
