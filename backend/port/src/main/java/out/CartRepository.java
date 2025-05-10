package out;

import model.cart.Cart;

import java.util.Optional;

/**
 * Secondary port for cart persistence operations.
 * Defines the contract for accessing and modifying cart data from a persistent store.
 * Implemented by adapters in the infrastructure layer (e.g., JPA, JDBC).
 */
public interface CartRepository {

    /**
     * Retrieves the cart associated with the given client ID, if it exists.
     *
     * @param clientId the identifier of the client
     * @return an Optional containing the cart if found, or empty if not present
     */
    Optional<Cart> findByClientId(long clientId);

    /**
     * Persists the given cart to the data store.
     * Can be used to create a new cart or update an existing one.
     *
     * @param cart the cart to be persisted
     * @return the saved cart instance (possibly with updated state, such as ID or timestamps)
     */
    Cart save(Cart cart);

    /**
     * Deletes the specified cart from the data store.
     * Typically used when clearing a cart or expiring stale ones.
     *
     * @param cartId the cart by id to be deleted
     */
    void deleteById(long cartId);

    /**
     * Expires carts that have been inactive for the given number of minutes.
     * This operation is usually triggered by a background scheduler and is responsible
     * for freeing up reserved inventory and maintaining a clean cart store.
     *
     * @param minutes the inactivity threshold in minutes; carts older than this will be expired
     */
    void expireCartsInactiveForMinutes(int minutes);
}
