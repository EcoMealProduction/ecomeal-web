package model.client;

import lombok.Builder;
import lombok.NonNull;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Restaurant;
import model.shared.Country;
import model.user.UserAccount;

import java.util.List;

/**
 * Represents a consumer using the platform to find and reserve surplus food.
 * Each client is associated with a user account, personal details, and geolocation data,
 * and has a defined maximum pickup radius used for filtering nearby restaurants.
 */

@Builder(toBuilder = true)
public record Client(
        long id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull UserAccount userAccount,
        Country country,
        Location location,
        @NonNull BankingDetails bankingDetails,
        double pickUpRadiusKm) {

    /**
     * Default constructor enforcing domain constraint on pickup radius.
     * Ensures the value remains within 0 to 100 km to prevent unrealistic queries.
     *
     * @throws PickUpRadiusOutOfRanceException if radius is outside the allowed bounds
     */
    public Client {
        if (pickUpRadiusKm < 0 || pickUpRadiusKm > 100)
            throw new PickUpRadiusOutOfRanceException("Pickup radius must be between 0 and 100 km.");
    }

    /**
     * Determines whether the client is within pickup range of the given restaurant.
     * Delegates to {@link Location#isWithinRadiusOf(Location, double)} for geospatial distance check.
     *
     * @param restaurant the restaurant to evaluate
     * @return true if the restaurant is within the client's pickup radius
     */
    public boolean canReach(Restaurant restaurant) {
        return this.location.isWithinRadiusOf(restaurant.location(), this.pickUpRadiusKm);
    }

    /**
     * Filters a list of restaurants to include only those within the client's pickup range.
     *
     * @param allRestaurants full list of available restaurants
     * @return a filtered list of reachable restaurants
     */
    public List<Restaurant> findNearbyRestaurants(List<Restaurant> allRestaurants) {
        return allRestaurants.stream()
                .filter(this::canReach)
                .toList();
    }
}
