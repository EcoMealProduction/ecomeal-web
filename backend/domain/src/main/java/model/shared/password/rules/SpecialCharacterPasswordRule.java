package model.shared.password.rules;

import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;

/**
 * Custom Passay rule that checks if the password contains at least one unicode special character.
 */
public class SpecialCharacterPasswordRule implements Rule {

    @Override
    public RuleResult validate(PasswordData passwordData) {
        boolean hasSpecialCharacter = passwordData.getPassword().codePoints().anyMatch(
                character -> !Character.isLetterOrDigit(character) && !Character.isWhitespace(character)
        );

        RuleResult result = new RuleResult();
        if (hasSpecialCharacter) result.setValid(true);
        else {
            result.setValid(false);
            result.getDetails().add(new RuleResultDetail("UNICODE_SPECIAL", null));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Password must contain at least one unicode special character character.";
    }
}
