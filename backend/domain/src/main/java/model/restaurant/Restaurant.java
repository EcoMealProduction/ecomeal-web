package model.restaurant;

import lombok.Builder;
import lombok.NonNull;
import model.payment.BankingDetails;
import model.user.UserAccount;

/**
 * Represents a food provider (e.g., restaurant, bakery, grocery store) registered in the platform.
 * Each restaurant is linked to a user account, location, and contact details.
 *
 * @param id Unique identifier of the restaurant; typically set by the database
 * @param userAccount Associated user credentials and role info
 * @param name Display name of the restaurant
 * @param description Optional description (e.g., cuisine, surplus policy)
 * @param location Physical location and coordinates for pickup
 * @param bankingDetails Linked banking information for payments/payouts
 */
@Builder(toBuilder = true)
public record Restaurant(
        long id,
        @NonNull UserAccount userAccount,
        @NonNull String name,
        String description,
        @NonNull Location location,
        @NonNull BankingDetails bankingDetails) {

}
