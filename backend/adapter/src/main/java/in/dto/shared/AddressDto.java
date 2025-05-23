package in.dto.shared;

/**
 * Data transfer object representing a full address as a single formatted string.
 *
 * @param value the full address (e.g., "123 Main St, City, ZIP")
 */
public record AddressDto(String value) {
}
