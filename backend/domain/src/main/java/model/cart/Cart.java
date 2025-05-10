package model.cart;

import lombok.Builder;
import lombok.NonNull;
import model.client.Client;
import model.shared.Money;

import java.util.List;

@Builder(toBuilder = true)
public record Cart(
        long id,
        @NonNull Client client,
        @NonNull Status status,
        @NonNull List<CartItem> cartItems) {

    public int quantity() {
        return cartItems.stream()
                .mapToInt(CartItem::quantity)
                .sum();
    }

    public Money totalPrice() {
        return cartItems.stream()
                .map(item -> item.product().getPrice().multiply(item.quantity()))
                .reduce(Money::add)
                .orElseThrow(() -> new IllegalStateException("Cart is empty"));
    }
}
