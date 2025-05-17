package service;

import in.CartUseCase;
import lombok.AllArgsConstructor;
import model.cart.Cart;
import model.cart.CartItem;
import model.cart.Status;
import model.client.Client;
import model.restaurant.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.CartRepository;
import out.ClientRepository;
import out.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Application service implementing cart-related use cases.
 * Handles adding, updating, removing, and clearing items in a client’s cart.
 * Acts as a primary port adapter in a hexagonal architecture.
 */
@Service
@AllArgsConstructor
public class CartService implements CartUseCase {

    private CartRepository cartRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;

    /**
     * Adds a product to the client's cart, increasing quantity if it already exists.
     *
     * @param clientId ID of the client
     * @param productId ID of the product to add
     * @param quantity number of units to add
     * @return updated {@link Cart}
     */
    @Override
    @Transactional
    public Cart addItemToCart(long clientId, long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");

        Client client = findClientById(clientId);
        Product product = findProductById(productId);
        Cart cart = cartRepository.findByClientId(clientId)
                .orElseGet(() -> Cart.builder()
                        .client(client)
                        .status(Status.ACTIVE)
                        .build());

        List<CartItem> updatedItems = new ArrayList<>(cart.cartItems());
        Optional<CartItem> existingItem = updatedItems.stream()
                .filter(item -> item.product().equals(product))
                .findFirst();

        Cart updatedCart;
        if (existingItem.isPresent()) {
            CartItem updatedItem = existingItem.get().increaseQuantity(quantity);
            updatedItems.remove(existingItem.get());
            updatedItems.add(updatedItem);
            updatedCart = cart.toBuilder()
                    .cartItems(updatedItems)
                    .build();
        } else {
            CartItem newItem = new CartItem(product.reserve(quantity), quantity);
            updatedItems.add(newItem);
            updatedCart = cart.toBuilder()
                    .cartItems(updatedItems)
                    .build();
        }

        return cartRepository.save(updatedCart);
    }

    /**
     * Removes a product from the client's cart and releases reserved quantity.
     *
     * @param clientId ID of the client
     * @param productId ID of the product to remove
     * @return updated {@link Cart}
     */
    @Override
    @Transactional
    public Cart removeItemFromCart(long clientId, long productId) {

        Product product = findProductById(productId);
        Cart cart = findCartByClientId(clientId);

        List<CartItem> updatedItems = cart.cartItems().stream()
                .filter(item -> !item.product().equals(product))
                .toList();

        Product releasedProduct = product.reduce(
                cart.cartItems().stream()
                        .filter(item -> item.product().equals(product))
                        .mapToInt(CartItem::quantity)
                        .sum()
        );
        productRepository.save(releasedProduct);

        Cart updatedCart = cart.toBuilder()
                .cartItems(updatedItems)
                .build();

        return cartRepository.save(updatedCart);
    }

    /**
     * Decreases the quantity of a product in the cart.
     *
     * @param clientId ID of the client
     * @param productId ID of the product to decrease
     * @param quantity amount to reduce
     * @return updated {@link Cart}
     */
    @Override
    @Transactional
    public Cart decreaseItemQuantity(long clientId, long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");

        Product product = findProductById(productId);
        Cart cart = findCartByClientId(clientId);

        List<CartItem> updatedItems = new ArrayList<>();

        for (CartItem cartItem : cart.cartItems()) {
            if (cartItem.product().equals(product)) {
                CartItem updatedItem = cartItem.decreaseQuantity(quantity);
                updatedItems.add(updatedItem);

                int releasedQuantity = cartItem.quantity() - quantity;
                if (releasedQuantity > 0) {
                    Product released = product.reduce(releasedQuantity);
                    productRepository.save(released);
                }
            } else {
                updatedItems.add(cartItem);
            }
        }

        Cart updatedCart = cart.toBuilder()
                .cartItems(updatedItems)
                .build();

        return cartRepository.save(updatedCart);
    }

    /**
     * Increases the quantity of a product in the cart.
     *
     * @param clientId ID of the client
     * @param productId ID of the product to increase
     * @param quantity additional quantity to add
     * @return updated {@link Cart}
     */
    @Override
    @Transactional
    public Cart increaseItemQuantity(long clientId, long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");

        Product product = findProductById(productId);
        Cart cart = findCartByClientId(clientId);

        List<CartItem> updatedItems = new ArrayList<>();

        for (CartItem cartItem : cart.cartItems()) {
            if (cartItem.product().equals(product)) {
                CartItem updatedItem = cartItem.increaseQuantity(quantity);
                updatedItems.add(updatedItem);

                int reservedQuantity = quantity - cart.quantity();
                if (reservedQuantity > 0) {
                    Product reserved = product.reserve(reservedQuantity);
                    productRepository.save(reserved);
                }
            } else {
                updatedItems.add(cartItem);
            }
        }

        Cart updatedCart = cart.toBuilder()
                .cartItems(updatedItems)
                .build();

        return cartRepository.save(updatedCart);
    }

    /**
     * Retrieves the active cart for a client.
     *
     * @param clientId ID of the client
     * @return the current {@link Cart}
     */
    @Override
    public Cart getCurrentCart(long clientId) {
        return findCartByClientId(clientId);
    }

    /**
     * Empties the cart and clears all product reservations.
     *
     * @param clientId ID of the client
     * @return an empty {@link Cart}
     */
    @Override
    @Transactional
    public Cart clearCart(long clientId) {
        Cart cart = findCartByClientId(clientId);

        for (CartItem cartItem : cart.cartItems()) {
            Product released = cartItem.product().reduce(cartItem.quantity());
            productRepository.save(released);
        }

        return cartRepository.save(cart.cleanCart());
    }

    /**
     * Expires carts inactive beyond the system-defined threshold.
     * To be triggered by a scheduled background task.
     */
    @Override
    public void expireInactiveCarts() {

    }

    private Client findClientById(long clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> new IllegalStateException("Client not found."));
    }

    private Product findProductById(long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found."));
    }

    private Cart findCartByClientId(long clientId) {
        return cartRepository.findByClientId(clientId).orElseThrow(
                () -> new IllegalStateException("No active cart found for client"));
    }
}
