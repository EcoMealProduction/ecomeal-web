package in;

/**
 * Primary port interface for handling the checkout process.
 * Encapsulates the core workflow for finalizing, canceling, confirming, or rolling back a checkout.
 *
 * This use case is part of the business layer and is implemented by application services.
 * It is driven by user actions or system events through adapters (e.g., REST, messaging).
 */
public interface CheckoutUseCase {

    /**
     * Initiates the checkout process for the specified client.
     * This step typically involves:
     * - Validating cart contents
     * - Locking product quantities
     * - Creating a pending order or reservation
     *
     * @param clientId the ID of the client initiating the checkout
     */
    void checkout(long clientId);

    /**
     * Cancels an ongoing or incomplete checkout for a client.
     * Reverses product reservations and clears temporary order state if applicable.
     *
     * @param clientId the ID of the client canceling the checkout
     */
    void cancelCheckout(long clientId);

    /**
     * Confirms payment for a previously checked-out cart.
     * Finalizes the transaction and marks the order as paid.
     *
     * @param cartId the ID of the cart or order being confirmed
     */
    void confirmPayment(long cartId);

    /**
     * Rolls back a previously started checkout due to failure (e.g., payment timeout, error).
     * Frees up reserved quantities and marks the transaction as failed or canceled.
     *
     * @param cartId the ID of the cart or order to roll back
     */
    void rollbackCheckout(long cartId);
}
