package in.dto.client;

import in.dto.payment.BankingDetailsDto;
import in.dto.shared.CountryDto;
import in.dto.shared.LocationDto;
import in.dto.user.UserAccountDto;
import lombok.Builder;
import lombok.NonNull;

/**
 * Data transfer object representing a client profile.
 * Encapsulates identity, account, location, and preference data for API responses or requests.
 *
 * @param firstName the client's first name
 * @param lastName the client's last name
 * @param userAccountDto associated user account information
 * @param country the client's selected country
 * @param locationDto geographic location of the client (optional)
 * @param bankingDetailsDto banking info used for payments or refunds (optional)
 * @param pickUpRadiusKm preferred maximum distance (in kilometers) for pickups
 */
@Builder(toBuilder = true)
public record ClientDto(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull UserAccountDto userAccountDto,
        @NonNull CountryDto country,
        LocationDto locationDto,
        BankingDetailsDto bankingDetailsDto,
        double pickUpRadiusKm) {
}
