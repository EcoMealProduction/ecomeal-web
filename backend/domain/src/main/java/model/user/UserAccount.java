package model.user;

import lombok.Builder;
import lombok.NonNull;
import model.shared.Email;
import model.shared.password.Password;
import model.shared.PhoneNumber;

/**
 * Represents a user's account with contact info, credentials, and role.
 *
 * @param email user's email
 * @param phoneNumber user's phone number
 * @param password user's password
 * @param role user's role
 */
@Builder(toBuilder = true)
public record UserAccount(
        long id,
        @NonNull Email email,
        @NonNull PhoneNumber phoneNumber,
        @NonNull Password password,
        @NonNull Role role) {
}
