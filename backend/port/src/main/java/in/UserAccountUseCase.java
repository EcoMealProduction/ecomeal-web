package in;

import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import model.user.UserAccount;

/**
 * Primary port interface for user account management use cases.
 * Defines high-level operations for querying, updating, and registering user accounts.
 * Implemented by application services that enforce business rules
 * and validations related to identity and contact information.
 */
public interface UserAccountUseCase {

    /**
     * Retrieves a user account based on the given email address.
     * Used primarily during authentication or profile lookup.
     *
     * @param email the email address to search by
     * @return the matching {@link UserAccount}, or throws an exception if not found
     */
    UserAccount findByEmail(Email email);

    /**
     * Checks whether a user account exists for the given email address.
     * Commonly used during registration to enforce uniqueness.
     *
     * @param email the email address to check
     * @return true if an account exists, false otherwise
     */
    boolean emailExists(Email email);

    /**
     * Updates the email address associated with a user account.
     * May require additional verification or checks depending on system policy.
     *
     * @param userAccountId the ID of the user account to update
     * @param newEmail the new email address to assign
     */
    void updateEmail(long userAccountId, Email newEmail);

    /**
     * Updates the phone number associated with a user account.
     * Can be used to update contact info or enable multi-factor authentication.
     *
     * @param userAccountId the ID of the user account to update
     * @param phoneNumber the new phone number to assign
     */
    void updatePhoneNumber(long userAccountId, PhoneNumber phoneNumber);

    /**
     * Changes the password for the specified user account.
     * Typically requires prior authentication or a secure reset flow.
     *
     * @param userAccountId the ID of the user account
     * @param newPassword the new password to set (should be pre-validated/hashed)
     */
    void changePassword(long userAccountId, Password newPassword);

    /**
     * Registers a new user account in the system.
     * May involve checking for existing email, validating credentials, and persisting the new account.
     *
     * @param userAccount the new account to register
     */
    void register(UserAccount userAccount);
}
