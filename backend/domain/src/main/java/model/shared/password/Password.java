package model.shared.password;

import lombok.NonNull;
import model.shared.password.rules.DigitPasswordRule;
import model.shared.password.rules.SpecialCharacterPasswordRule;
import model.shared.password.rules.UpperCasePasswordRule;
import org.passay.*;

import java.util.List;

/**
 * Represents a user's password as a non-null value, allows encapsulation and future validation or hashing.
 *
 * @param value the password value
 */
public record Password(@NonNull String value) {

    public Password {
        if (value.isEmpty()) throw new IllegalArgumentException("'value' must not be empty.");
        validatePassword(value);
    }

    /**
     * Validates the password using custom rules for length, uppercase, digit, and special character;
     * throws an exception if the password doesn't meet all criteria
     */
    private void validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator(List.of(
                new LengthRule(8, 30),
                new UpperCasePasswordRule(),
                new DigitPasswordRule(),
                new SpecialCharacterPasswordRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (!result.isValid())
            throw new IllegalArgumentException("Password validation failed: "
                    + String.join(", ", validator.getMessages(result)));
    }
}
