package in.dto.shared;

import lombok.NonNull;

/**
 * Data transfer object representing a country, including its name and ISO code.
 *
 * @param name the full name of the country (e.g., "Germany")
 * @param isoCountryCode the ISO 3166-1 alpha-2 country code (e.g., "DE")
 */
public record CountryDto(
        @NonNull String name,
        @NonNull String isoCountryCode) {
}
