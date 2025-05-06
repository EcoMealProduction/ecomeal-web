package model.shared;

import lombok.NonNull;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Encapsulates an email address as a non-null value to ensure type safety and potential.
 *
 * @param value the email address value
 */
public record Email(@NonNull String value) {

    public Email {
        if (!EmailValidator.getInstance().isValid(value))
            throw new IllegalArgumentException("Invalid email format");
    }

}
