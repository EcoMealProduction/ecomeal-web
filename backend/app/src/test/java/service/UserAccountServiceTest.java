package service;

import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import model.user.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.UserAccountRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static service.Fixtures.vaneaUserAccount;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock private UserAccountRepository userAccountRepository;
    @InjectMocks private UserAccountService userAccountService;

    @Test
    public void testFindUserAccountById() {
        when(userAccountRepository.findById(vaneaUserAccount.id())).thenReturn(Optional.of(vaneaUserAccount));
        UserAccount foundUserAccount = userAccountService.findById(vaneaUserAccount.id());

        verify(userAccountRepository, times(1)).findById(vaneaUserAccount.id());

        assertEquals(vaneaUserAccount, foundUserAccount);
    }

    @Test
    public void testFindUserAccountByEmail() {
        when(userAccountRepository.findByEmail(vaneaUserAccount.email())).thenReturn(Optional.of(vaneaUserAccount));
        UserAccount foundUserAccount = userAccountService.findByEmail(vaneaUserAccount.email());

        verify(userAccountRepository, times(1)).findByEmail(vaneaUserAccount.email());

        assertEquals(vaneaUserAccount, foundUserAccount);
    }

    @Test
    public void testExistingUserAccountEmail() {
        when(userAccountRepository.existsByEmail(vaneaUserAccount.email())).thenReturn(true);

        assertTrue(userAccountService.emailExists(vaneaUserAccount.email()));
    }

    @Test
    public void testUpdateUserAccountEmail() {
        Email newEmail = new Email("newEmail@gmail.com");
        UserAccount expectedUserAccount = vaneaUserAccount.toBuilder()
                .email(newEmail)
                .build();
        when(userAccountRepository.findById(vaneaUserAccount.id())).thenReturn(Optional.of(vaneaUserAccount));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(expectedUserAccount);

        UserAccount updatedUserAccount = userAccountService.updateEmail(vaneaUserAccount.id(), newEmail);

        assertEquals(expectedUserAccount.email(), updatedUserAccount.email());

    }

    @Test
    public void testUpdateUserAccountPhoneNumber() {
        PhoneNumber newPhoneNumber = new PhoneNumber("+37360654681");
        UserAccount expectedUserAccount = vaneaUserAccount.toBuilder()
                .phoneNumber(newPhoneNumber)
                .build();
        when(userAccountRepository.findById(vaneaUserAccount.id())).thenReturn(Optional.of(vaneaUserAccount));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(expectedUserAccount);

        UserAccount updatedUserAccount = userAccountService.updatePhoneNumber(vaneaUserAccount.id(), newPhoneNumber);

        assertEquals(expectedUserAccount.phoneNumber(), updatedUserAccount.phoneNumber());
    }

    @Test
    public void testChangeUserAccountPassword() {
        Password newPassword = new Password("newPassword123!");
        UserAccount expectedUserAccount = vaneaUserAccount.toBuilder()
                .password(newPassword)
                .build();
        when(userAccountRepository.findById(vaneaUserAccount.id())).thenReturn(Optional.of(vaneaUserAccount));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(expectedUserAccount);

        UserAccount updatedUserAccount = userAccountService.changePassword(vaneaUserAccount.id(), newPassword);

        assertEquals(expectedUserAccount.password(), updatedUserAccount.password());
    }
}
