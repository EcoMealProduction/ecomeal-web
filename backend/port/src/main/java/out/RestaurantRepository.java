package out;

import model.restaurant.Restaurant;

import java.util.Optional;

/**
 * Secondary port for managing persistence of {@link Restaurant} entities.
 * Defines the contract for interacting with the data store to retrieve, save, or delete restaurant information.
 * Implemented by infrastructure-layer adapters (e.g., JPA, JDBC).
 */
public interface RestaurantRepository {

    /**
     * Finds a restaurant by its unique identifier.
     *
     * @param restaurantId the ID of the restaurant to retrieve
     * @return an Optional containing the restaurant if found, or empty if not found
     */
    Optional<Restaurant> findById(long restaurantId);

    /**
     * Saves or updates the given restaurant in the data store.
     * Can be used to create a new restaurant record or persist changes to an existing one.
     *
     * @param restaurant the restaurant to save
     * @return the persisted restaurant instance, possibly with updated fields (e.g., ID or timestamps)
     */
    Restaurant save(Restaurant restaurant);

    /**
     * Deletes a restaurant from the data store by its unique identifier.
     *
     * @param restaurantId the ID of the restaurant to delete
     */
    void deleteById(long restaurantId);
}
