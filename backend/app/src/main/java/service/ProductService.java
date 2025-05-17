package service;

import in.ProductUseCase;
import lombok.AllArgsConstructor;
import model.restaurant.Product;
import model.restaurant.Restaurant;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.ProductRepository;
import out.RestaurantRepository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Application service implementing {@link ProductUseCase}.
 * Provides business operations for managing products offered by restaurants,
 * including creation, updates, stock reservation, and availability queries.
 */
@Service
@AllArgsConstructor
public class ProductService implements ProductUseCase {

    private ProductRepository productRepository;
    private RestaurantRepository restaurantRepository;

    /**
     * Finds a product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the found product
     * @throws IllegalStateException if no product is found with the given ID
     */
    @Override
    public Product findById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found. "));
    }

    /**
     * Retrieves a paginated list of available products.
     *
     * @param page the page number to retrieve (0-based)
     * @return a list of available products on the requested page
     */
    @Override
    public List<Product> findAvailableProducts(int page) {
        final int size = 10;
        return productRepository.findAvailableProducts(PageRequest.of(page, size));
    }

    /**
     * Returns all products listed by a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @return a list of products offered by the restaurant
     */
    @Override
    public List<Product> findAllByRestaurant(long restaurantId) {
        return productRepository.findAllByRestaurantId(restaurantId);
    }

    /**
     * Creates a new product for the specified restaurant.
     *
     * @param restaurantId the ID of the restaurant creating the product
     * @param product the product to be created
     * @return the saved product with restaurant information
     * @throws IllegalStateException if the restaurant does not exist
     * @throws IllegalArgumentException if the pickup time is in the past
     */
    @Override
    @Transactional
    public Product createProduct(long restaurantId, Product product) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalStateException("Restaurant not found."));

        Product productWithRestaurant = product.toBuilder()
                .restaurant(restaurant)
                .build();

        if (product.pickUpTime().isBefore(OffsetDateTime.now()))
            throw new IllegalArgumentException("Pickup time must be in the future.");

        return productRepository.save(productWithRestaurant);
    }

    /**
     * Updates an existing product by its ID.
     *
     * @param productId the ID of the product to update
     * @param product the new product data
     * @return the updated product
     * @throws IllegalStateException if the product does not exist
     * @throws IllegalArgumentException if the pickup time is in the past
     */
    @Override
    @Transactional
    public Product updateProduct(long productId, Product product) {
        Product existingProduct = findById(productId);

        if (product.pickUpTime().isBefore(OffsetDateTime.now()))
            throw new IllegalArgumentException("Pickup time must be in the future.");

        Product updatedProduct = existingProduct.toBuilder()
                .name(product.name())
                .restaurant(product.restaurant())
                .description(product.description())
                .pickUpTime(product.pickUpTime())
                .price(product.price())
                .reservedQuantity(product.reservedQuantity())
                .totalQuantity(product.totalQuantity())
                .build();

        return productRepository.save(updatedProduct);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     */
    @Override
    @Transactional
    public void deleteProductById(long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Reduces the available quantity of a product.
     *
     * @param productId the ID of the product
     * @param quantity the quantity to reduce
     * @return the updated product
     * @throws IllegalStateException if the product does not exist
     * @throws IllegalArgumentException if quantity is not positive
     */
    @Override
    @Transactional
    public Product reduceQuantity(long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");

        Product product = findById(productId);
        Product updatedQuantityProduct = product.reduce(quantity);

        return productRepository.save(updatedQuantityProduct);
    }

    /**
     * Reserves a quantity of product stock.
     *
     * @param productId the ID of the product
     * @param quantity the quantity to reserve
     * @return the updated product
     * @throws IllegalStateException if the product does not exist
     * @throws IllegalArgumentException if quantity is not positive
     */
    @Override
    @Transactional
    public Product reserveQuantity(long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");

        Product product = findById(productId);
        Product updatedQuantityProduct = product.reserve(quantity);

        return productRepository.save(updatedQuantityProduct);
    }
}
