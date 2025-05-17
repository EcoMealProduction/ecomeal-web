package in;

import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Product;
import model.restaurant.Restaurant;

import java.util.List;
import java.util.Optional;

/**
 * Primary port interface for managing restaurant-related use cases.
 * Defines business operations for retrieving and modifying restaurant data,
 * including location, banking details, and associated product listings.
 *
 * This interface is implemented by application services and called by driving adapters (e.g., REST controllers).
 */
public interface RestaurantUseCase {

    /**
     * Retrieves a restaurant by its unique identifier.
     *
     * @param restaurantId the ID of the restaurant to look up
     * @return an Optional containing the restaurant if found, or empty if not found
     */
    Restaurant findRestaurantById(long restaurantId);

    /**
     * Creates a new restaurant entry in the system.
     * This typically occurs during onboarding of a business partner.
     *
     * @param restaurant the restaurant entity to create
     * @return the persisted restaurant instance with any generated values (e.g., ID)
     */
    Restaurant createNewRestaurant(Restaurant restaurant);

    /**
     * Updates the location data (address and coordinates) of the specified restaurant.
     * This affects geolocation-based search and pickup logic.
     *
     * @param restaurantId the ID of the restaurant to update
     * @param updatedLocation the new location to apply
     * @return the updated restaurant instance
     */
    Restaurant updateLocation(long restaurantId, Location updatedLocation);

    /**
     * Updates the banking details associated with the restaurant.
     * Required for processing payouts or financial verifications.
     *
     * @param restaurantId the ID of the restaurant to update
     * @param updatedBankingDetails the new banking details
     * @return the updated restaurant instance
     */
    Restaurant updateBankingDetails(long restaurantId, BankingDetails updatedBankingDetails);

    /**
     * Replaces the list of products offered by the restaurant.
     * This method overwrites the existing product list with the new one provided.
     * Domain rules (e.g., max number of products) may be enforced.
     *
     * @param restaurantId the ID of the restaurant updating its products
     * @param products the full list of products to associate with the restaurant
     * @return the updated restaurant instance
     */
    Restaurant updateProductList(long restaurantId, List<Product> products);
}
