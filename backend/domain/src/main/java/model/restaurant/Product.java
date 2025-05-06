package model.restaurant;

import lombok.NonNull;
import model.shared.Money;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Immutable representation of a surplus product offered by a restaurant.
 * Encapsulates product details, quantity available, pricing, pickup scheduling,
 * and the restaurant that owns the product listing.
 */
public record Product(
        @NonNull String name,
        @NonNull Optional<String> description,
        int quantity,
        @NonNull Money price,
        @NonNull OffsetDateTime pickUpTime,
        @NonNull Restaurant restaurant) {

    public Product {
        if (quantity <= 0) throw new InvalidQuantityException("Product quantity must be at least 1.");
    }

    public Money originalPrice() {
        return price.multiply(2);
    }

    // TODO: the quantity is used in product page as well to pick the desired quantity, so it will be range until
    //         a certain number. More than that, the quantity must not be exceeded.
}
