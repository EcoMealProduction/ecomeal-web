package in;

import model.restaurant.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Primary port interface for managing product-related use cases.
 * Defines application-level operations for creating, updating, querying, and modifying products
 * offered by restaurants. Implemented by application services within the core business layer.
 */
public interface ProductUseCase {

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId the ID of the product to retrieve
     * @return the product instance
     */
    Product findById(long productId);

    /**
     * Retrieves available products by page number.
     *
     * @param page the page number
     * @return the list of products on the required page
     */
    Page<Product> findAvailableProducts(int page, int size);

    /**
     * Retrieves all products belonging to a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @return a list of products offered by the specified restaurant
     */
    List<Product> findAllByRestaurant(long restaurantId);

    /**
     * Creates a new product and associates it with a given restaurant.
     * Performs validation such as max product limits or pricing constraints.
     *
     * @param restaurantId the ID of the restaurant offering the product
     * @param product the product to create
     * @return the newly created product instance
     */
    Product createProduct(long restaurantId, Product product);

    /**
     * Updates an existing product by its ID.
     * Typically, includes changes to fields like quantity, price, or pickup time.
     *
     * @param productId the ID of the product to update
     * @return the updated product instance
     */
    Product updateProduct(long productId, Product product);

    /**
     * Deletes a product by its unique identifier.
     * Used when the product is no longer available or the listing should be removed.
     *
     * @param productId the ID of the product to delete
     */
    void deleteProductById(long productId);

    /**
     * Decreases the quantity of a product by the specified amount.
     * This is used after successful cart operations or reservations.
     * Enforces validation to prevent reducing below zero.
     *
     * @param productId the ID of the product to modify
     * @param quantity the amount to subtract from the product's total quantity
     */
    Product reduceQuantity(long productId, int quantity);

    /**
     * Increase the quantity of a product by the specified amount.
     * This is used after successful cart operations or reservations.
     * Enforces validation to prevent reducing below zero.
     *
     * @param productId the ID of the product to modify
     * @param quantity the amount to add from the product's total quantity
     */
    Product reserveQuantity(long productId, int quantity);
}
