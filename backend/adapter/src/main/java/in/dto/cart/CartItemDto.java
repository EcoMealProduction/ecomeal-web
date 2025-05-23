package in.dto.cart;

import in.dto.restaurant.ProductDto;
import lombok.NonNull;

/**
 * Data transfer object representing a single item in the cart.
 * Includes product details and the quantity selected by the client.
 *
 * @param productDto the product associated with this cart item
 * @param quantity the number of units of the product in the cart
 */
public record CartItemDto(
        @NonNull ProductDto productDto,
        int quantity) {
}
