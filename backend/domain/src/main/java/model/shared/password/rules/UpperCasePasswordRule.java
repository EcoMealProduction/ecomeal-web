package model.shared.password.rules;

import org.passay.PasswordData;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;

/**
 * Custom Passay rule that checks if the password contains at least one unicode uppercase character.
 */
public class UpperCasePasswordRule implements Rule {
    @Override
    public RuleResult validate(PasswordData passwordData) {
        boolean hasUpperCaseLetter = passwordData.getPassword().codePoints().anyMatch(Character::isUpperCase);

        RuleResult result = new RuleResult();

        if (hasUpperCaseLetter)
            result.setValid(true);
        else {
            result.setValid(false);
            result.getDetails().add(new RuleResultDetail("UNICODE_UPPERCASE", null));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Password must contain at least one uppercase unicode character.";
    }
}
