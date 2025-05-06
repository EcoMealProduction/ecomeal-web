package shared;

import model.shared.Money;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static shared.MoneyFactoryTest.euros;
import static shared.MoneyFactoryTest.usDollars;

public class MoneyTest {

    private static final Currency EUR = Currency.getInstance("EUR");

    @Test
    void givenAmountWithAnInvalidScale_newMoney_throwsIllegalArgumentException() {
        BigDecimal amountWithScale3 = new BigDecimal(BigInteger.valueOf(12999), 3);

        ThrowableAssert.ThrowingCallable invocation = () -> new Money(EUR, amountWithScale3);

        assertThatIllegalArgumentException().isThrownBy(invocation);
    }

    @Test
    void givenAEuroAmount_addADollarAmount_throwsIllegalArgumentException() {
        Money euros = euros(11, 99);
        Money dollars = usDollars(11, 99);

        ThrowableAssert.ThrowingCallable invocation = () -> euros.add(dollars);

        assertThatIllegalArgumentException().isThrownBy(invocation);
    }
}
