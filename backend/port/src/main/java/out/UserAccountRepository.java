package out;

import model.shared.Email;
import model.user.UserAccount;

import java.util.Optional;

/**
 * Secondary port for managing persistence of {@link UserAccount} entities.
 * Defines repository operations used by the application layer to interact with stored user account data.
 * Implemented by infrastructure adapters (e.g., JPA repository) and used by services
 * that manage authentication, registration, and account lookups.
 */
public interface UserAccountRepository {

    /**
     * Retrieves a user account by its email address.
     * Typically used during authentication or to check for duplicate accounts during registration.
     *
     * @param email the email address to search by
     * @return an Optional containing the user account if found, or empty if not found
     */
    Optional<UserAccount> findByEmail(Email email);

    /**
     * Finds a user account by its unique identifier.
     *
     * @param userAccountId the ID of the user account
     * @return an Optional containing the account if found, or empty if not found
     */
    Optional<UserAccount> findById(long userAccountId);

    /**
     * Persists the given user account to the database.
     * Used for both creating new accounts and updating existing ones.
     *
     * @param userAccount the user account to save
     * @return the saved user account instance
     */
    UserAccount save(UserAccount userAccount);

    /**
     * Checks whether a user account with the given email address already exists.
     * Typically used to enforce unique email constraints.
     *
     * @param email the email address to check
     * @return true if an account with the given email exists, false otherwise
     */
    boolean existsByEmail(Email email);
}
