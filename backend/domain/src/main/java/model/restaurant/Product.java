package model.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import model.restaurant.exception.InvalidQuantityException;
import model.shared.Money;

import java.time.OffsetDateTime;

/**
 * Represents a surplus product listed by a restaurant, with inventory management capabilities.
 * Maintains reservation state and enforces constraints on stock operations and product validity.
 */
@Builder(toBuilder = true)
public record Product(
        long id,
        @NonNull String name,
        @NonNull String description,
        int totalQuantity,
        int reservedQuantity,
        Restaurant restaurant,
        @NonNull Money price,
        @NonNull OffsetDateTime pickUpTime) {

    /**
     * Sentinel value indicating non-persisted product
     */
    public static final long UNSAVED_ID = -1;

    public Product {
        if (totalQuantity < 0)
            throw new IllegalArgumentException("totalQuantity cannot be negative");

        if (reservedQuantity < 0 || reservedQuantity > totalQuantity)
            throw new IllegalArgumentException("reservedQuantity is invalid");

    }

    /**
     * Computes the number of units that are still available for reservation.
     *
     * @return the difference between total and reserved quantities
     */
    public int availableQuantity() {
        return this.totalQuantity - this.reservedQuantity;
    }

    /**
     * Returns an updated total quantity after reserving the given quantity.
     *
     * @param quantity the number of units to reserve
     *
     * @throws InvalidQuantityException if quantity is non-positive or exceeds availableQuantity()
     */
    public Product reserve(int quantity) {
        if (quantity <= 0 || quantity > availableQuantity())
            throw new InvalidQuantityException("Not enough stock available.");

        return this.toBuilder()
                .reservedQuantity(this.reservedQuantity + quantity)
                .build();
    }

    public Product reduce(int quantity) {
        if (quantity <= 0 || quantity > reservedQuantity)
            throw new InvalidQuantityException("Cannot release more than reserved.");

        return this.toBuilder()
                .reservedQuantity(this.reservedQuantity - quantity)
                .build();
    }

    /**
     * Returns the full price before discount. This example assumes a fixed 50% discount on the listed price.
     *
     * @return the original (non-discounted) price
     */
    public Money getOriginalPrice() {
        return price.multiply(2);
    }

    /**
     * Indicates whether the product has been persisted (saved in the database).
     *
     * @return true if the product has a valid ID; false otherwise
     */
    public boolean isPersisted() {
        return this.id != UNSAVED_ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product other)) return false;
        return this.id == other.id;
    }
}
