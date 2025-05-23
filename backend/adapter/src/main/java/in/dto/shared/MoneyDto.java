package in.dto.shared;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Data transfer object representing a monetary value with its associated currency.
 *
 * @param currency the currency of the amount (e.g., EUR, USD)
 * @param amount the numeric value of the money (non-negative, with correct scale)
 */
public record MoneyDto(@NonNull Currency currency, @NonNull BigDecimal amount) {
}
