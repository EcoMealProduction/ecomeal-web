package service;

import in.UserAccountUseCase;
import lombok.AllArgsConstructor;
import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import model.user.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import out.UserAccountRepository;

/**
 * Application service for user account operations.
 * Implements {@link UserAccountUseCase} and acts as a primary port adapter.
 * Handles identity, contact, and credential-related use cases.
 */
@Service
@AllArgsConstructor
public class UserAccountService implements UserAccountUseCase {

    private UserAccountRepository userAccountRepository;

    /**
     * Retrieves a user account by its ID.
     *
     * @param userAccountId the ID of the user account
     * @return the matching {@link UserAccount}
     * @throws IllegalStateException if not found
     */
    @Override
    public UserAccount findById(long userAccountId) {
        return userAccountRepository.findById(userAccountId).orElseThrow(
                () -> new IllegalStateException("User not found"));
    }

    /**
     * Retrieves a user account by its email.
     *
     * @param email the email address to look up
     * @return the matching {@link UserAccount}
     * @throws IllegalStateException if not found
     */
    @Override
    public UserAccount findByEmail(Email email) {
        return userAccountRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User not found"));
    }

    /**
     * Checks if an account with the given email already exists.
     *
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    @Override
    public boolean emailExists(Email email) {
        return userAccountRepository.existsByEmail(email);
    }

    /**
     * Updates the email of a user account.
     *
     * @param userAccountId the ID of the account to update
     * @param newEmail the new email address
     * @return the updated {@link UserAccount}
     */
    @Override
    @Transactional
    public UserAccount updateEmail(long userAccountId, Email newEmail) {
        UserAccount existingUserAccount = findById(userAccountId);
        UserAccount updatedUserAccount = existingUserAccount.toBuilder()
                .email(newEmail)
                .build();

        return userAccountRepository.save(updatedUserAccount);
    }

    /**
     * Updates the phone number of a user account.
     *
     * @param userAccountId the ID of the account to update
     * @param newPhoneNumber the new phone number
     * @return the updated {@link UserAccount}
     */
    @Override
    @Transactional
    public UserAccount updatePhoneNumber(long userAccountId, PhoneNumber newPhoneNumber) {
        UserAccount existingUserAccount = findById(userAccountId);
        UserAccount updatedUserAccount = existingUserAccount.toBuilder()
                .phoneNumber(newPhoneNumber)
                .build();

        return userAccountRepository.save(updatedUserAccount);
    }

    /**
     * Changes the password of a user account.
     *
     * @param userAccountId the ID of the account
     * @param newPassword the new password (validated and hashed)
     * @return the updated {@link UserAccount}
     */
    @Override
    @Transactional
    public UserAccount changePassword(long userAccountId, Password newPassword) {
        UserAccount existingUserAccount = findById(userAccountId);
        UserAccount updatedUserAccount = existingUserAccount.toBuilder()
                .password(newPassword)
                .build();

        return userAccountRepository.save(updatedUserAccount);
    }
}
