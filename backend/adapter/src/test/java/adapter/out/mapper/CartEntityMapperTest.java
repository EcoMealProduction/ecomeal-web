package adapter.out.mapper;

import model.cart.Cart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.cart.CartEntity;
import out.mapper.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static adapter.Fixtures.vaneasCart;


@ContextConfiguration(classes = {
        CartEntityMapperImpl.class,
        CartItemEntityMapperImpl.class,
        UserAccountEntityMapperImpl.class,
        ProductEntityMapperImpl.class,
        RestaurantEntityMapperImpl.class,
        BankingDetailsEntityMapperImpl.class
})
@ExtendWith(SpringExtension.class)
public class CartEntityMapperTest {

    @Autowired private CartEntityMapper cartEntityMapper;

    @Test
    public void testMapToCartEntity() {
        CartEntity cartEntity = cartEntityMapper.toCartEntity(vaneasCart);

        assertEquals(cartEntity.getClient().getFirstName(), vaneasCart.client().firstName());
        assertEquals(cartEntity.getClient().getLastName(), vaneasCart.client().lastName());
        assertEquals(cartEntity.getCartItems().getFirst().getProduct().getName(), vaneasCart.cartItems().getFirst().product().name());
        assertEquals(cartEntity.getCartItems().getLast().getProduct().getName(), vaneasCart.cartItems().getLast().product().name());
    }

    @Test
    public void testMapToCart() {
        CartEntity cartEntity = cartEntityMapper.toCartEntity(vaneasCart);
        Cart cart = cartEntityMapper.toCart(cartEntity);

        assertEquals(cart.client().firstName(), vaneasCart.client().firstName());
        assertEquals(cart.client().lastName(), vaneasCart.client().lastName());
        assertEquals(cart.cartItems().getFirst().product().name(), vaneasCart.cartItems().getFirst().product().name());
        assertEquals(cart.cartItems().getLast().product().name(), vaneasCart.cartItems().getLast().product().name());
    }
}
