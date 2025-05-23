package in.dto.shared;

import lombok.NonNull;

/**
 * Data transfer object representing a phone number.
 *
 * @param value the phone number string (should include country code and follow expected format)
 */
public record PhoneNumberDto(@NonNull String value) {
}
