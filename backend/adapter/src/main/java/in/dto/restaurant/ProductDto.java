package in.dto.restaurant;

import in.dto.shared.MoneyDto;
import lombok.Builder;
import lombok.NonNull;

import java.time.OffsetDateTime;

/**
 * Data transfer object representing a surplus product offered by a restaurant.
 * Contains product metadata, inventory details, pricing, and pickup scheduling.
 *
 * @param name the name of the product
 * @param description a short description of the product
 * @param totalQuantity total units available for reservation
 * @param reservedQuantity number of units already reserved
 * @param restaurantDto the restaurant offering the product (optional)
 * @param price the discounted price of the product
 * @param pickUpTime the scheduled time window for product pickup
 */
@Builder(toBuilder = true)
public record ProductDto(
        @NonNull String name,
        @NonNull String description,
        int totalQuantity,
        int reservedQuantity,
        RestaurantDto restaurantDto,
        @NonNull MoneyDto price,
        @NonNull OffsetDateTime pickUpTime) {
}
