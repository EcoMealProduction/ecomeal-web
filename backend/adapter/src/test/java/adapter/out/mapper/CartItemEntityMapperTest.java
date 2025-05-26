package adapter.out.mapper;

import model.cart.CartItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.cart.CartItemEntity;
import out.mapper.*;

import static adapter.Fixtures.placinteInCart;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {
        CartItemEntityMapperImpl.class,
        UserAccountEntityMapperImpl.class,
        ProductEntityMapperImpl.class,
        RestaurantEntityMapperImpl.class,
        BankingDetailsEntityMapperImpl.class
})
@ExtendWith(SpringExtension.class)
public class CartItemEntityMapperTest {

    @Autowired private CartItemEntityMapper cartItemEntityMapper;

    @Test
    void testMapToCartItemEntity() {
        CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(placinteInCart);

        assertEquals(placinteInCart.product().name(), cartItemEntity.getProduct().getName());
        assertEquals(placinteInCart.product().reservedQuantity(), cartItemEntity.getProduct().getReservedQuantity());
        assertEquals(placinteInCart.product().price().amount(), cartItemEntity.getProduct().getPriceAmount());
        assertEquals(placinteInCart.quantity(), cartItemEntity.getQuantity());
    }

    @Test
    void testMapToCartItem() {
        CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(placinteInCart);
        CartItem cartItem = cartItemEntityMapper.toCartItem(cartItemEntity);

        assertEquals(placinteInCart.product().name(), cartItem.product().name());
        assertEquals(placinteInCart.product().reservedQuantity(), cartItem.product().reservedQuantity());
        assertEquals(placinteInCart.product().price().amount(), cartItem.product().price().amount());
        assertEquals(placinteInCart.quantity(), cartItem.quantity());
    }
}
