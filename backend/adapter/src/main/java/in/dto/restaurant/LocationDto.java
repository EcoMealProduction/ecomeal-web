package in.dto.restaurant;

import in.dto.shared.AddressDto;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Data transfer object representing a geographic location.
 * Combines address information with precise coordinates.
 *
 * @param addressDto the structured address (street, city, postal code, etc.)
 * @param latitude the latitude coordinate (must be between -90 and 90)
 * @param longitude the longitude coordinate (must be between -180 and 180)
 */
public record LocationDto(
        @NonNull AddressDto addressDto,
        @NonNull BigDecimal latitude,
        @NonNull BigDecimal longitude) {
}
