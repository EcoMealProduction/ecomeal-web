package model.unit.restaurant;

import model.restaurant.exception.InvalidQuantityException;
import model.restaurant.Product;
import model.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import static model.Fixtures.andys;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Currency eur;
    private Product plashinti;

    @BeforeEach
    public void setup() {
        eur = Currency.getInstance("EUR");
        Money bakshish = new Money(eur, new BigDecimal(12));
        OffsetDateTime pickUpTime = OffsetDateTime.now().plusHours(3);

        plashinti = Product.builder()
                .id(1L)
                .name("Plashinti")
                .description("gustoase ca la matta")
                .totalQuantity(10)
                .restaurant(andys)
                .price(bakshish)
                .pickUpTime(pickUpTime)
                .build();
    }

    @Test
    public void testInvalidReservedProduct() {
        InvalidQuantityException exception = assertThrows(InvalidQuantityException.class,
                () -> plashinti.reserve(16));

        assertTrue(exception.getMessage().contains("Not enough stock available"));
    }

    @Test
    public void testReservedProduct() {
        Product reservedProduct = plashinti.reserve(5);

        assertEquals(5, reservedProduct.availableQuantity());
    }

    @Test
    public void testInvalidReleasedProduct() {
        InvalidQuantityException exception = assertThrows(InvalidQuantityException.class,
                () -> plashinti.reduce(6));

        assertTrue(exception.getMessage().contains("Cannot release more than reserved"));
    }

    @Test
    public void testReleasedProduct() {
        Product reservedProduct = plashinti.reserve(5);
        Product releasedProduct = reservedProduct.reduce(4);

        assertEquals(9, releasedProduct.availableQuantity());
    }


}
