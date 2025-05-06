package model.shared;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Immutable representation of a monetary value, combining a {@link Currency}
 * and a {@link BigDecimal} amount. Enforces currency-specific scale constraints
 * and ensures safe arithmetic operations within the same currency context.
 */
public record Money(@NonNull Currency currency, @NonNull BigDecimal amount) {

    /**
     * Compact constructor that validates the scale of the monetary amount
     * against the currency's default fraction digits. Throws an exception
     * if the precision exceeds what is valid for the specified currency.
     */
    public Money {
        if (amount.scale() > currency.getDefaultFractionDigits())
            throw new IllegalArgumentException((
                    "Scale of amount %s is greater than the number of fraction digits used with currency %s")
                            .formatted(amount, currency));
    }

    /**
     * Factory method to create a {@code Money} instance using major and minor units.
     * Useful for constructing amounts without manually calculating scale.
     *
     * @param currency the currency to associate with the amount
     * @param major the whole unit portion (e.g., euros or dollars)
     * @param minor the fractional unit portion (e.g., cents)
     * @return a new {@code Money} instance with combined major and minor value
     */
    public static Money of(Currency currency, int major, int minor) {
        int scale = currency.getDefaultFractionDigits();
        return new Money(currency, BigDecimal.valueOf(major).add(BigDecimal.valueOf(minor, scale)));
    }

    /**
     * Returns a new {@code Money} instance representing the sum of this and another {@code Money}.
     * Both amounts must be in the same currency; otherwise, an exception is thrown.
     *
     * @param augend the {@code Money} value to add
     * @return a new {@code Money} instance with the combined amount
     * @throws IllegalArgumentException if the currencies do not match
     */
    public Money add(Money augend) {
        if (!this.currency.equals(augend.currency))
            throw new IllegalArgumentException("Currency %s of augend does not match this money's currency %s"
                            .formatted(augend.currency(), this.currency));

        return new Money(currency, amount.add(augend.amount()));
    }

    /**
     * Returns a new {@code Money} instance representing this amount multiplied by the given integer.
     * Currency remains unchanged; scale is preserved based on multiplication result.
     *
     * @param multiplicand the integer value to multiply the amount by
     * @return a new {@code Money} instance with the scaled amount
     */
    public Money multiply(int multiplicand) {
        return new Money(currency, amount.multiply(BigDecimal.valueOf(multiplicand)));
    }
}
