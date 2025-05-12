package model.unit.client;

import model.client.Client;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.shared.Address;
import model.shared.Country;
import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import model.user.Role;
import model.user.UserAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ClientTest {
    private final Email email = new Email("vasea1@gmail.com");
    private final PhoneNumber phoneNumber = new PhoneNumber("+37360724176");
    private final Password password = new Password("Vasea123!");
    private final UserAccount userAccount = new UserAccount(1L, email, phoneNumber, password, Role.CLIENT);
    private Country country = new Country("Moldova", "MD");

    private final Address aachenerDomAddress = new Address("Domhof 1, 52062 Aachen");
    private final BigDecimal aachenerDomLatitude = BigDecimal.valueOf(50.77473538509855);
    private final BigDecimal aachenerDomLongitude = BigDecimal.valueOf(6.083920847968691);
    private final Location aachenerDom = new Location(aachenerDomAddress, aachenerDomLatitude, aachenerDomLongitude);

    private final Address aachenerNobisAddress = new Address("Münsterpl. 3, 52062 Aachen");
    private final BigDecimal aachenerNobisLatitude = BigDecimal.valueOf(50.774640969053024);
    private final BigDecimal aachenerNobisLongitude = BigDecimal.valueOf(6.08490228561747);
    private final Location aachenerNobis = new Location(aachenerNobisAddress, aachenerNobisLatitude, aachenerNobisLongitude);


    private final BankingDetails bankingDetails = new BankingDetails(
            1L, "da", country, "", "", "");

    private final Client vasea = new Client(
            1L, "Vasea", "Cerdacu", userAccount,
            country, aachenerDom, bankingDetails, 12);

    // TODO: create test fixtures before writing this test, its too long :0
    @Test
    void testClientCanReachRestaurant() {

    }


}
