package in.unit.mapper;

import in.dto.cart.CartDto;
import in.dto.cart.CartItemDto;
import in.mapper.CartMapper;
import in.mapper.CartMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.Fixtures.*;

@ContextConfiguration(classes = CartMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class CartMapperTest {

    @Autowired private CartMapper cartMapper;

    @Test
    public void testMapToCartDto() {
        CartDto cartDto = cartMapper.toCartDto(vaneasCart);

        assertEquals(cartDto.clientDto().firstName(), vaneasCart.client().firstName());
        assertEquals(cartDto.cartItemDtos().getFirst().productDto().name(), placinte.name());
        assertEquals(cartDto.cartItemDtos().getLast().productDto().name(), jumeri.name());
        assertEquals(cartDto.status(), vaneasCart.status());
    }

    @Test
    public void testMapToCartItemDto() {
        CartItemDto cartItemDto = cartMapper.toCartItemDto(placinteInCart);

        assertEquals(cartItemDto.productDto().name(), placinte.name());
        assertEquals(cartItemDto.quantity(), placinteInCart.quantity());
        assertEquals(cartItemDto.productDto().reservedQuantity(), placinteInCart.product().reservedQuantity());
    }

}
