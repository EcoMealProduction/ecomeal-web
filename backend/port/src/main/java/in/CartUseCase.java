package in;

import model.cart.Cart;

/**
 * Inbound port interface for managing a client's cart.
 * Defines core cart operations such as item manipulation, cart retrieval, and cleanup routines.
 * Implemented by application services that contain the business logic for cart behavior.
 *
 * In hexagonal architecture, this serves as a primary input boundary between the domain/application layer
 * and the driving adapter (e.g., REST controller or scheduler).
 */
public interface CartUseCase {

    /**
     * Adds a new item to the client's active cart.
     * If the cart does not exist, a new one is created.
     * If the item already exists, its quantity is incremented by one.
     *
     * @param clientId ID of the client performing the action
     * @param productId ID of the product to be added
     * @param quantity the number of units to be added
     * @return the updated Cart instance
     */
    Cart addItemToCart(long clientId, long productId, int quantity);

    /**
     * Removes a given quantity of a product from the client's cart.
     * If the quantity reaches zero, the item is removed entirely.
     * If the product is not in the cart, no action is taken or an exception may be thrown (implementation-defined).
     *
     * @param clientId ID of the client performing the action
     * @param productId ID of the product to remove
     * @param quantity the number of units to remove
     * @return the updated Cart instance
     */
    Cart removeItemFromCart(long clientId, long productId, int quantity);

    /**
     * Updates the quantity of a specific product in the cart to the specified value.
     * If the quantity is zero, the item is removed.
     * May trigger validation against available stock or product constraints.
     *
     * @param clientId ID of the client performing the update
     * @param productId ID of the product to modify
     * @param newQuantity the new quantity to set
     * @return the updated Cart instance
     */
    Cart updateItemQuantity(long clientId, long productId, int newQuantity);

    /**
     * Retrieves the client's current cart.
     * May return an empty cart if none exists or has been cleared.
     *
     * @param clientId ID of the client whose cart is being queried
     * @return the current Cart instance
     */
    Cart getCurrentCart(long clientId);

    /**
     * Empties the contents of the client's cart.
     * All reserved quantities are released and the cart is reset.
     *
     * @param clientId ID of the client performing the action
     */
    void clearCart(long clientId);

    /**
     * Scheduled or manual task to expire carts that have been inactive beyond a defined timeout threshold.
     * This frees up reserved inventory and keeps system state consistent.
     * Typically invoked by a background scheduler (driven adapter).
     */
    void expireInactiveCarts();
}
