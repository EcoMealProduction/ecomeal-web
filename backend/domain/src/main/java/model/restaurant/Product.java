package model.restaurant;

import lombok.NonNull;
import model.shared.Money;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Represents a surplus product listed by a restaurant, with inventory management capabilities.
 * Maintains reservation state and enforces constraints on stock operations and product validity.
 *
 * @param id Unique identifier of the product; -1 if not yet persisted
 * @param name Name/title of the product
 * @param description Optional description (e.g., contents, quality notes)
 * @param totalQuantity Total available quantity for the product
 * @param reservedQuantity Currently reserved quantity (e.g., already purchased)
 * @param price Price per unit, including currency
 * @param pickUpTime Time slot for when the product must be picked up
 * @param restaurant Associated restaurant offering the product
 */
public record Product(
        long id,
        @NonNull String name,
        String description,
        int totalQuantity,
        int reservedQuantity,
        @NonNull Money price,
        @NonNull OffsetDateTime pickUpTime,
        @NonNull Restaurant restaurant) {

    /**
     * Sentinel value indicating non-persisted product
     */
    public static final long UNSAVED_ID = -1;

    /**
     * Validates inventory invariants: no negative quantities,
     * and reserved quantity must not exceed total available.
     */
    public Product {
        if (totalQuantity < 0 || reservedQuantity < 0 || reservedQuantity > totalQuantity)
            throw new InvalidQuantityException("Invalid inventory state.");
    }

    /**
     * Returns a new Product instance with updated reservation count after reserving the given quantity.
     *
     * @param quantity the number of units to reserve
     *
     * @return a new Product instance with increased reservedQuantity
     * @throws InvalidQuantityException if quantity is non-positive or exceeds availableQuantity()
     */
    public Product reserve(int quantity) {
        if (quantity <= 0 || quantity > availableQuantity())
            throw new InvalidQuantityException("Not enough stock available.");

        return new Product(id, name, description, totalQuantity,
                reservedQuantity + quantity, price, pickUpTime, restaurant);
    }

    /**
     * Returns a new Product instance with updated reservation count after releasing the given quantity.
     *
     * @param quantity the number of units to release from reservation
     *
     * @return a new Product instance with decreased reservedQuantity
     * @throws InvalidQuantityException if quantity is non-positive or exceeds reserved quantity
     */
    public Product release(int quantity) {
        if (quantity <= 0 || quantity > reservedQuantity)
            throw new InvalidQuantityException("Cannot release more than reserved.");

        return new Product(id, name, description, totalQuantity,
                reservedQuantity - quantity, price, pickUpTime, restaurant);
    }

    /**
     * Computes the number of units that are still available for reservation.
     *
     * @return the difference between total and reserved quantities
     */
    public int availableQuantity() {
        return totalQuantity - reservedQuantity;
    }

    /**
     * Returns the full price before discount. This example assumes a fixed 50% discount on the listed price.
     *
     * @return the original (non-discounted) price
     */
    public Money originalPrice() {
        return price.multiply(2);
    }

    /**
     * Indicates whether the product has been persisted (saved in the database).
     *
     * @return true if the product has a valid ID; false otherwise
     */
    public boolean isPersisted() {
        return id != UNSAVED_ID;
    }

}
