package in.dto.restaurant;

import in.dto.payment.BankingDetailsDto;
import in.dto.user.UserAccountDto;
import lombok.Builder;
import lombok.NonNull;

/**
 * Data transfer object representing a restaurant on the platform.
 * Contains identity, business location, and payment configuration details.
 *
 * @param userAccountDto the user account associated with the restaurant
 * @param name the display name of the restaurant
 * @param description an optional description of the restaurant
 * @param locationDto the restaurant’s geographic location and address
 * @param bankingDetailsDto the restaurant’s banking or billing information
 */
@Builder(toBuilder = true)
public record RestaurantDto(
        @NonNull UserAccountDto userAccountDto,
        @NonNull String name,
        String description,
        @NonNull LocationDto locationDto,
        @NonNull BankingDetailsDto bankingDetailsDto) {
}
