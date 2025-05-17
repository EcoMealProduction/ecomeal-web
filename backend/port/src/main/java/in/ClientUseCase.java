package in;

import model.client.Client;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Restaurant;
import model.shared.Country;

import java.util.List;
import java.util.Optional;

/**
 * Primary port interface for handling client-related use cases.
 * Defines operations that allow interaction with the {@link Client} domain model,
 * such as retrieval, updates to preferences and location, and querying nearby restaurants.
 *
 * This interface is implemented by application services in the application core
 * and used by driving adapters such as REST controllers or schedulers.
 */
public interface ClientUseCase {

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param clientId the ID of the client
     * @return an Optional containing the client if found, or empty if not
     */
    Client findClientById(long clientId);

    /**
     * Registers a new client in the system.
     * Typically called during onboarding or user sign-up.
     *
     * @param client the client entity to be persisted
     * @return the newly created and saved client instance
     */
    Client createNewClient(Client client);

    /**
     * Updates the selected country for a client.
     * Useful when the client changes their region or relocates.
     *
     * @param clientId the ID of the client to update
     * @param newSelectedCountry the newly selected country
     * @return the updated Client instance with the new country
     */
    Client updateCountry(long clientId, Country newSelectedCountry);

    /**
     * Updates the geographic location of a client.
     * This affects radius-based queries such as nearby restaurant searches.
     *
     * @param clientId the ID of the client to update
     * @param updatedLocation the new location data (address and coordinates)
     * @return the updated Client instance with the new location
     */
    Client updateLocation(long clientId, Location updatedLocation);

    /**
     * Updates the banking details associated with a client.
     * Used for enabling payments, refunds, or financial verification.
     *
     * @param clientId the ID of the client to update
     * @param updatedBankingDetails the new banking information
     * @return the updated Client instance with new banking details
     */
    Client updateBankingDetails(long clientId, BankingDetails updatedBankingDetails);

    /**
     * Updates the maximum pickup radius for a client.
     * This radius determines how far they are willing to travel to collect orders.
     *
     * @param clientId the ID of the client to update
     * @param updatedPickUpRadius the new radius in kilometers
     * @return the updated Client instance with new radius
     */
    Client updatePickUpRadius(long clientId, double updatedPickUpRadius);

    /**
     * Returns a list of restaurants that are within the client’s pickup radius.
     * Uses the client’s current location and search radius to filter a given list of restaurants.
     *
     * @param clientId the ID of the client performing the search
     * @param allRestaurants the complete list of restaurants to filter
     * @return a filtered list containing only restaurants reachable by the client
     */
    List<Restaurant> findNearbyRestaurants(long clientId, List<Restaurant> allRestaurants);
}
