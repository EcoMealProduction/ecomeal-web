package model.shared.password.rules;

import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;

/**
 * Custom Passay rule that checks if the password contains at least one unicode digit character.
 */
public class DigitPasswordRule implements Rule {
    @Override
    public RuleResult validate(PasswordData passwordData) {
        boolean hasDigit = passwordData.getPassword().codePoints().anyMatch(Character::isDigit);

        RuleResult result = new RuleResult();

        if (hasDigit) result.setValid(true);
        else {
            result.setValid(false);
            result.getDetails().add(new RuleResultDetail("UNICODE_DIGIT", null));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Password must contain at least one unicode digit character.";
    }
}
