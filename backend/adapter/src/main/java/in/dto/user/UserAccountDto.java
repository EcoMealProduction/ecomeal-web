package in.dto.user;

import in.dto.shared.EmailDto;
import in.dto.shared.PasswordDto;
import in.dto.shared.PhoneNumberDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.NonNull;
import model.user.Role;

/**
 * Data transfer object representing a user's account credentials and role.
 * Used during registration, login, and account updates.
 *
 * @param emailDto the user's email address (must be valid and not blank)
 * @param phoneNumberDto the user's phone number (must not be blank)
 * @param passwordDto the user's plaintext password (must not be blank)
 * @param role the assigned role of the user (e.g., CLIENT, RESTAURANT)
 */
@Builder(toBuilder = true)
public record UserAccountDto(
        @Email
        @NotBlank(message = "Email is mandatory")
        @NonNull
        EmailDto emailDto,

        @NotBlank(message = "phone number is mandatory")
        @NonNull
        PhoneNumberDto phoneNumberDto,

        @NotBlank(message = "Password is mandatory")
        @NonNull
        PasswordDto passwordDto,

        @NotBlank(message = "Choosing role is mandatory")
        @NonNull
        Role role) {
}
