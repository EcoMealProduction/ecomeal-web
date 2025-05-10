package out;

import model.restaurant.Product;

import java.util.List;
import java.util.Optional;

/**
 * Secondary port for managing persistent access to {@link Product} entities.
 * Implemented by infrastructure-layer adapters (e.g., JPA, JDBC) to encapsulate database operations.
 * Exposes product lookup, persistence, and deletion operations.
 */
public interface ProductRepository {

    /**
     * Finds a product by its unique identifier.
     *
     * @param productId the ID of the product to retrieve
     * @return an Optional containing the product if found, or empty if not present
     */
    Optional<Product> findById(long productId);

    /**
     * Retrieves all products associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant whose products are being queried
     * @return a list of products owned or created by the given restaurant
     */
    List<Product> findAllByRestaurantId(long restaurantId);

    /**
     * Saves a product to the persistent store.
     * Can be used to create new products or update existing ones.
     *
     * @param product the product to save
     * @return the saved product instance, possibly updated with new state (e.g., ID)
     */
    Product save(Product product);

    /**
     * Deletes a product from the persistent store.
     * May be used when a product is no longer available or needs to be removed manually.
     *
     * @param productId the product by id to delete
     */
    void deleteById(long productId);
}
