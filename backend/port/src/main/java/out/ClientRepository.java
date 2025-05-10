package out;

import model.client.Client;

import java.util.Optional;

/**
 * Secondary port for accessing and modifying {@link Client} entities in persistent storage.
 * This repository interface is implemented by infrastructure-layer adapters (e.g., JPA, MongoDB),
 * and is used by application services to perform client-related data operations.
 */
public interface ClientRepository {

    /**
     * Finds a client by their unique identifier.
     *
     * @param clientId the ID of the client to retrieve
     * @return an Optional containing the client if found, or empty if not found
     */
    Optional<Client> findById(long clientId);

    /**
     * Persists the given client to the database.
     * Can be used to create a new client or update an existing one.
     *
     * @param client the client entity to be saved
     * @return the saved client, potentially updated with generated values (e.g., ID)
     */
    Client save(Client client);

    /**
     * Deletes the client with the specified ID from persistent storage.
     *
     * @param clientId the ID of the client to delete
     */
    void deleteById(long clientId);
}
