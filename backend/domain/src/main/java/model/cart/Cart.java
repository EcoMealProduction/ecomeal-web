package model.cart;

import lombok.Builder;
import lombok.NonNull;
import model.client.Client;
import model.restaurant.Product;
import model.shared.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable shopping cart tied to a specific client.
 * Holds selected items, their quantities, and current checkout status.
 * All modifications return a new cart instance.
 */
@Builder(toBuilder = true)
public record Cart(
        long id,
        @NonNull Client client,
        @NonNull Status status,
        @NonNull List<CartItem> cartItems) {

    /**
     * Validates and ensures immutability of the cart item list.
     * Prevents external mutation by wrapping with an unmodifiable list.
     */
    public Cart {
        cartItems = cartItems == null ? List.of() : Collections.unmodifiableList(new ArrayList<>(cartItems));
    }


    /**
     * Computes the total quantity of items across all cart entries.
     *
     * @return total number of units in the cart
     */
    public int quantity() {
        return cartItems.stream()
                .mapToInt(CartItem::quantity)
                .sum();
    }

    /**
     * Calculates the total price of all items in the cart.
     * Throws if the cart is empty.
     *
     * @return aggregated monetary value of the cart
     */
    public Money totalPrice() {
        return cartItems.stream()
                .map(item -> item.product().getPrice().multiply(item.quantity()))
                .reduce(Money::add)
                .orElseThrow(() -> new IllegalStateException("Cart is empty"));
    }

    /**
     * Adds a new item to the cart or increases quantity if the product already exists.
     * Also updates the product reservation through the CartItem logic.
     *
     * @param product the product to add or update
     * @param quantity number of units to add
     * @return a new Cart instance with updated items
     */
    public Cart addItem(Product product, int quantity) {
        final List<CartItem> updatedItems = new ArrayList<>();
        boolean found = false;

        for (CartItem item : cartItems) {
            if (item.product().equals(product)) {
                updatedItems.add(item.increaseQuantity(quantity));
                found = true;
            } else {
                updatedItems.add(item);
            }
        }
        if (!found) {
            updatedItems.add(new CartItem(product.reserve(quantity), quantity));
        }

        return this.toBuilder()
                .cartItems(updatedItems)
                .status(Status.ACTIVE)
                .build();
    }

    /**
     * Removes an item from the cart by product identity.
     *
     * @param product the product to remove
     * @return a new Cart instance without the product
     */
    public Cart removeItem(Product product) {
        List<CartItem> updatedItems = cartItems.stream()
                .filter(item -> !item.product().equals(product))
                .toList();

        return this.toBuilder()
                .cartItems(updatedItems)
                .status(Status.ACTIVE)
                .build();
    }

    /**
     * Empties the cart of all items.
     *
     * @return a new Cart with no items
     */
    public Cart cleanCart() {
        return this.toBuilder()
                .cartItems(List.of())
                .status(Status.EXPIRED)
                .build();
    }
}
