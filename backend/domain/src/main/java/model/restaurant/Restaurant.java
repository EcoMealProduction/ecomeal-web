package model.restaurant;

import lombok.NonNull;
import model.payment.BankingDetails;
import model.restaurant.exception.TooManyProductsException;
import model.user.UserAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a food provider (e.g., restaurant, bakery, grocery store) registered in the platform.
 * Each restaurant is linked to a user account and maintains product listings, location, and contact details.
 *
 * @param id Unique identifier of the restaurant; typically set by the database
 * @param userAccount Associated user credentials and role info
 * @param name Display name of the restaurant
 * @param description Optional description (e.g., cuisine, surplus policy)
 * @param location Physical location and coordinates for pickup
 * @param products List of available surplus products
 * @param bankingDetails Linked banking information for payments/payouts
 */
public record Restaurant(
        long id,
        @NonNull UserAccount userAccount,
        @NonNull String name,
        String description,
        @NonNull Location location,
        @NonNull List<Product> products,
        @NonNull BankingDetails bankingDetails) {

    /**
     * Validates and sanitizes product list:
     * - Replaces null with an empty immutable list
     * - Wraps list in unmodifiable wrapper to preserve immutability
     * - Enforces a cap of 100 products per restaurant to prevent abuse or overload
     *
     * @throws TooManyProductsException if more than 100 products are associated
     */
    public Restaurant {
        products = products == null ? List.of() : Collections.unmodifiableList(new ArrayList<>(products));

        if (products.size() > 100) throw new TooManyProductsException("Too many products.");
    }
}
