package restaurant;

import model.restaurant.InvalidQuantityException;
import model.restaurant.Product;
import model.restaurant.Restaurant;
import model.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product jumeri;
    private Currency eur;

    @BeforeEach
    public void setup() {
        eur = Currency.getInstance("EUR");
        Money bakshish = new Money(eur, new BigDecimal(12));
        OffsetDateTime pickUpTime = OffsetDateTime.now().plusHours(3);
        Restaurant andys = new Restaurant();

        jumeri = new Product(
                1L,
                "Jumeri",
                "gustoase ca la matta",
                5, 0,
                bakshish,
                pickUpTime,
                andys);
    }

    @Test
    public void testInvalidReservedProduct() {
        InvalidQuantityException exception = assertThrows(InvalidQuantityException.class,
                () -> jumeri.reserve(6));

        assertTrue(exception.getMessage().contains("Not enough stock available"));
    }

    @Test
    public void testReservedProduct() {
        Product reservedProduct = jumeri.reserve(5);

        assertEquals(5, reservedProduct.reservedQuantity());
    }

    @Test
    public void testInvalidReleasedProduct() {
        InvalidQuantityException exception = assertThrows(InvalidQuantityException.class,
                () -> jumeri.release(6));

        assertTrue(exception.getMessage().contains("Cannot release more than reserved"));
    }

    @Test
    public void testReleasedProduct() {
        Product reservedProduct = jumeri.reserve(5);
        Product releasedProduct = reservedProduct.release(4);

        assertEquals(1, releasedProduct.reservedQuantity());
    }

    @Test
    public void testProductOriginalPrice() {
        final Money originalBakshish = new Money(eur, new BigDecimal(24));

        assertEquals(jumeri.originalPrice(), originalBakshish);
    }

}
