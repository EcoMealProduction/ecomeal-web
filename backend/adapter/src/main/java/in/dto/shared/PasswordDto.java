package in.dto.shared;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

/**
 * Data transfer object representing a raw (unhashed) password value.
 * Used during registration or credential updates. Should be validated and secured before persistence.
 *
 * @param value the plaintext password string (must not be blank)
 */
public record PasswordDto(@NotBlank @NonNull String value) {
}
