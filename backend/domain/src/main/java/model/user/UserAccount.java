package model.user;

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
public record UserAccount(Email email, PhoneNumber phoneNumber, Password password, Role role) {
}
