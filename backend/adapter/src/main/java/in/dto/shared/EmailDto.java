package in.dto.shared;

import lombok.NonNull;

/**
 * Data transfer object representing an email address.
 *
 * @param value the email address string (must be non-null and properly formatted)
 */
public record EmailDto(@NonNull String value) {
}
