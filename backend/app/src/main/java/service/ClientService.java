package service;

import in.ClientUseCase;
import lombok.AllArgsConstructor;
import model.client.Client;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Restaurant;
import model.shared.Country;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.ClientRepository;

import java.util.List;

/**
 * Application service implementing client-related use cases.
 * Handles profile updates, client lookup, and nearby restaurant filtering.
 * Acts as a primary port adapter in a hexagonal architecture.
 */
@Service
@AllArgsConstructor
public class ClientService implements ClientUseCase {

    private ClientRepository clientRepository;

    /**
     * Retrieves a client by ID or throws if not found.
     *
     * @param clientId the ID of the client
     * @return the client instance
     * @throws IllegalStateException if the client does not exist
     */
    @Override
    public Client findClientById(long clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> new IllegalStateException("Client not found."));
    }

    /**
     * Persists a new client in the system.
     *
     * @param client the client to create
     * @return the saved client instance
     */
    @Override
    @Transactional
    public Client createNewClient(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Updates the country associated with the client.
     *
     * @param clientId the client to update
     * @param newSelectedCountry the new country
     * @return the updated client
     */
    @Override
    @Transactional
    public Client updateCountry(long clientId, Country newSelectedCountry) {
        Client client = findClientById(clientId);
        Client updatedClient = client.toBuilder()
                .country(newSelectedCountry)
                .build();

        return clientRepository.save(updatedClient);
    }

    /**
     * Updates the client's current location (address and coordinates).
     *
     * @param clientId the client to update
     * @param updatedLocation the new location
     * @return the updated client
     */
    @Override
    @Transactional
    public Client updateLocation(long clientId, Location updatedLocation) {
        Client client = findClientById(clientId);
        Client updatedClient = client.toBuilder()
                .location(updatedLocation)
                .build();

        return clientRepository.save(updatedClient);
    }

    /**
     * Updates the client's banking details.
     *
     * @param clientId the client to update
     * @param updatedBankingDetails the new banking info
     * @return the updated client
     */
    @Override
    @Transactional
    public Client updateBankingDetails(long clientId, BankingDetails updatedBankingDetails) {
        Client client = findClientById(clientId);
        Client updatedClient = client.toBuilder()
                .bankingDetails(updatedBankingDetails)
                .build();

        return clientRepository.save(updatedClient);
    }

    /**
     * Updates the maximum pickup radius the client is willing to travel.
     *
     * @param clientId the client to update
     * @param updatedPickUpRadius the new radius in kilometers
     * @return the updated client
     */
    @Override
    @Transactional
    public Client updatePickUpRadius(long clientId, double updatedPickUpRadius) {
        Client client = findClientById(clientId);
        Client updatedClient = client.toBuilder()
                .pickUpRadiusKm(updatedPickUpRadius)
                .build();

        return clientRepository.save(updatedClient);
    }

    /**
     * Filters the list of restaurants to those within the client's pickup radius.
     *
     * @param clientId the client performing the search
     * @param allRestaurants the full list of available restaurants
     * @return a list of reachable restaurants
     */
    @Override
    public List<Restaurant> findNearbyRestaurants(long clientId, List<Restaurant> allRestaurants) {
        Client client = findClientById(clientId);
        return client.findNearbyRestaurants(allRestaurants);
    }
}
