package model.payment;

import model.shared.Country;

public record BankingDetails(
        long id,
        String name,
        Country country,
        // TODO: probably it requires VO extension.
        String postalCode,
        String city,
        String street
) {
}
