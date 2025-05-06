package model.shared;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.NonNull;

/**
 * Wraps a phone number value to ensure type safety and enforce non-null constraint.
 *
 * @param value the phone number value
 */
public record PhoneNumber(@NonNull String value) {

    public PhoneNumber {
        validatePhoneNumber(value);
    }

    private void validatePhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, null);

            if (!phoneNumberUtil.isValidNumber(parsedPhoneNumber))
                throw new IllegalArgumentException("Invalid phone number format");

        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Unable to parse phone number: " + e.getMessage());
        }
    }
}
