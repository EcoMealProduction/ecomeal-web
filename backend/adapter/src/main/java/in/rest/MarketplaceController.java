package in.rest;

import in.dto.restaurant.ProductDto;
import in.mapper.ProductMapper;
import model.restaurant.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.List;

/**
 * REST controller for the marketplace interface.
 * Exposes product browsing endpoints for consumers, including paginated listings
 * and restaurant-specific product queries.
 */
@RestController
@RequestMapping("/api/v1/products")
public class MarketplaceController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public MarketplaceController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * Retrieves a paginated list of all available products.
     *
     * @param page the page number (1-based, default is 1)
     * @param size the number of products per page (default is 10)
     * @return a list of available {@link ProductDto}s
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAvailableProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Product> pageProducts = productService.findAvailableProducts(page, size);
        List<ProductDto> dtoPage = pageProducts.getContent()
                .stream()
                .map(productMapper::toProductDto)
                .toList();

        return ResponseEntity.ok(dtoPage);
    }

    /**
     * Retrieves all products listed by a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant
     * @return a list of {@link ProductDto}s offered by the restaurant
     */
    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<ProductDto>> findAllByRestaurant(
            @PathVariable long restaurantId) {

        return null;
    }

}
