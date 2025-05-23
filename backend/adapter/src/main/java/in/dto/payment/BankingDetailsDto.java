package in.dto.payment;

import model.shared.Country;

/**
 * Data transfer object representing a client's banking and billing details.
 *
 * @param name the account holder's name
 * @param country the country associated with the bank or billing address
 * @param postalCode the postal (ZIP) code of the billing address
 * @param city the city of the billing address
 * @param street the street name and number of the billing address
 */
public record BankingDetailsDto(
        String name,
        Country country,
        String postalCode,
        String city,
        String street) { }
