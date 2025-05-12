package model.unit.shared;

import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAccountCredentialsTest {

    @Test
    public void testPasswordCreationThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Password("gigel"));

        assertTrue(exception.getMessage().contains("Password validation failed"));
    }

    @Test
    public void shouldNotThrowExceptionForStrongPassword() {
        String strongPassword = "Cumatrelu1!";
        new Password(strongPassword);
    }

    @Test
    public void testInvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Email("hehe"));

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    public void testValidEmail() {
        new Email("hehe@gmail.com");
    }

    @Test
    public void testInvalidPhoneNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new PhoneNumber("+311234"));

        assertTrue(exception.getMessage().contains("Invalid phone number format"));
    }

    @Test
    public void testUnableParsePhoneNumber() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new PhoneNumber("1234"));

        assertTrue(exception.getMessage().contains("Unable to parse phone number"));
    }

    @Test
    public void testValidPhoneNumber() {
        new PhoneNumber("+4917677478603");
    }

}
