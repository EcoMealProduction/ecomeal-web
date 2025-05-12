package model.unit.cart;

import model.cart.Cart;
import model.cart.CartItem;
import model.cart.Status;
import model.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static model.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartTest {

    private Cart cart;

    @BeforeEach
    public void setup() {
        List<CartItem> items = List.of(plashintiInCart, jumeriInCart);
        cart = Cart.builder()
                .id(1L)
                .client(vanea)
                .status(Status.ACTIVE)
                .cartItems(items)
                .build();
    }

    @Test
    public void testQuantityAndTotalPrice() {
        assertEquals(8, cart.quantity());
        assertEquals(new Money(eur, BigDecimal.valueOf(96)), cart.totalPrice());
    }

    @Test
    public void testManipulateItems() {
        cart = cart.addItem(chifla, 3);

        assertEquals(cart.cartItems(), List.of(plashintiInCart, jumeriInCart, chiflaInCart));
        assertEquals(new Money(eur, BigDecimal.valueOf(132)), cart.totalPrice());
        assertEquals(11, cart.quantity());

        cart = cart.removeItem(chifla);

        assertEquals(cart.cartItems(), List.of(plashintiInCart, jumeriInCart));
        assertEquals(8, cart.quantity());
        assertEquals(new Money(eur, BigDecimal.valueOf(96)), cart.totalPrice());

        cart = cart.cleanCart();

        assertEquals(cart.cartItems(), List.of());
        assertEquals(0, cart.quantity());

        assertThrows(IllegalStateException.class, () -> cart.totalPrice());
    }
}
