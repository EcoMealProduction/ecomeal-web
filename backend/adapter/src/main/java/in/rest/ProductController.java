package in.rest;

import in.dto.restaurant.ProductDto;
import in.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import model.restaurant.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

/**
 * REST controller for managing restaurant products.
 * Exposes endpoints for creating, updating, retrieving, deleting, and adjusting product stock.
 */
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to fetch
     * @return the product DTO if found
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable long productId) {
        Product product = productService.findById(productId);
        ProductDto productDto = productMapper.toProductDto(product);

        return ResponseEntity.ok(productDto);
    }

    /**
     * Creates a new product under a given restaurant.
     *
     * @param restaurantId the ID of the restaurant creating the product
     * @param product the product data from the request body
     * @return the created product DTO
     */
    @PostMapping("/restaurants/{restaurantId}/products")
    public ResponseEntity<ProductDto> createProduct(
            @PathVariable long restaurantId,
            @Valid @RequestBody ProductDto product) {

        Product mappedProduct = productMapper.toProduct(product);
        Product createdProduct = productService.createProduct(restaurantId, mappedProduct);
        ProductDto productDto = productMapper.toProductDto(createdProduct);

        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    /**
     * Updates an existing product.
     *
     * @param productId the ID of the product to update
     * @param product the updated product data
     * @return the updated product DTO
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable long productId,
            @Valid @RequestBody ProductDto product) {

        Product mappedProduct = productMapper.toProduct(product);
        Product createdProduct = productService.updateProduct(productId, mappedProduct);
        ProductDto productDto = productMapper.toProductDto(createdProduct);

        return ResponseEntity.ok(productDto);
    }

    /**
     * Deletes a product by ID.
     *
     * @param productId the ID of the product to delete
     * @return HTTP 204 if deleted, 404 if not found
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Reduces the available quantity of a product.
     *
     * @param productId the ID of the product
     * @param quantity the amount to reduce
     * @return the updated product DTO
     */
    @PatchMapping("/products/{productId}/quantity/reduce")
    public ResponseEntity<ProductDto> reduceQuantity(
            @PathVariable long productId,
            @RequestParam int quantity) {

        Product product = productService.reduceQuantity(productId, quantity);
        ProductDto productDto = productMapper.toProductDto(product);

        return ResponseEntity.ok(productDto);
    }

    /**
     * Reserves a quantity of stock for a product.
     *
     * @param productId the ID of the product
     * @param quantity the amount to reserve
     * @return the updated product DTO
     */
    @PatchMapping("/products/{productId}/quantity/reserve")
    public ResponseEntity<ProductDto> reserveQuantity(
            @PathVariable long productId,
            @RequestParam int quantity) {

        Product product = productService.reserveQuantity(productId, quantity);
        ProductDto productDto = productMapper.toProductDto(product);

        return ResponseEntity.ok(productDto);
    }
}
