package model.unit.cart;

import model.cart.CartItem;
import model.restaurant.Product;
import model.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartItemTest {

    private Currency eur;
    private Product plashinti;
    private CartItem plashintiInCart;

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
                .price(bakshish)
                .pickUpTime(pickUpTime)
                .build();

        plashintiInCart = new CartItem(plashinti, 3);
    }

    @Test
    public void testQuantityManagement() {
        CartItem increasedQuantity = plashintiInCart.increaseQuantity(3);
        assertEquals(6, increasedQuantity.quantity());
        CartItem decreasedQuantity = increasedQuantity.decreaseQuantity(2);
        assertEquals(4, decreasedQuantity.quantity());
    }

}
