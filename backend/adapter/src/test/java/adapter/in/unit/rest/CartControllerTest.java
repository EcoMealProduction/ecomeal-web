package adapter.in.unit.rest;


import in.dto.cart.CartDto;
import in.mapper.CartMapperImpl;
import in.rest.CartController;
import model.cart.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import service.CartService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static adapter.Fixtures.*;

@ContextConfiguration(classes = {CartMapperImpl.class})
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock private CartService cartService;
    @Autowired private CartMapperImpl cartMapper = new CartMapperImpl();
    private CartController controller;

    @BeforeEach
    public void setup() {
        controller = new CartController(cartService, cartMapper);
    }

    @Test
    public void testAddItemToCart() {
        Cart updatedCart = vaneasCart.addItem(jumeri, 3);
        CartDto updatedCartDto = cartMapper.toCartDto(updatedCart);
        when(cartService.addItemToCart(vanea.id(), jumeri.id(), 3)).thenReturn(updatedCart);
        ResponseEntity<CartDto> response = controller.addItemToCart(vanea.id(), jumeri.id(), 3);

        assertUpdatedCart(updatedCartDto, response, HttpStatus.CREATED);
    }

    @Test
    public void testRemoveItemFromCart() {
        Cart updatedCart = vaneasCart.removeItem(jumeri);
        CartDto updatedCartDto = cartMapper.toCartDto(updatedCart);
        when(cartService.removeItemFromCart(vanea.id(), jumeri.id())).thenReturn(updatedCart);
        ResponseEntity<CartDto> response = controller.removeItemFromCart(vanea.id(), jumeri.id());

        assertUpdatedCart(updatedCartDto, response, HttpStatus.OK);
    }

    @Test
    public void testDecreaseItemQuantity() {
        Cart updatedCart = vaneasCart.removeItem(jumeri);
        CartDto updatedCartDto = cartMapper.toCartDto(updatedCart);

        when(cartService.decreaseItemQuantity(vanea.id(), jumeri.id(), 2)).thenReturn(updatedCart);

        ResponseEntity<CartDto> response = controller.decreaseItemQuantity(vanea.id(), jumeri.id(), 2);

        assertUpdatedCart(updatedCartDto, response, HttpStatus.OK);
    }

    @Test
    public void testIncreaseItemQuantity() {
        Cart updatedCart = vaneasCart.addItem(jumeri, 2);
        CartDto updatedCartDto = cartMapper.toCartDto(updatedCart);

        when(cartService.increaseItemQuantity(vanea.id(), jumeri.id(), 2)).thenReturn(updatedCart);

        ResponseEntity<CartDto> response = controller.increaseItemQuantity(vanea.id(), jumeri.id(), 2);

        assertUpdatedCart(updatedCartDto, response, HttpStatus.OK);
    }

    @Test
    public void testGetCurrentCart() {
        Cart currentCart = vaneasCart;
        CartDto cartDto = cartMapper.toCartDto(currentCart);

        when(cartService.getCurrentCart(vanea.id())).thenReturn(currentCart);

        ResponseEntity<CartDto> response = controller.getCurrentCart(vanea.id());

        assertUpdatedCart(cartDto, response, HttpStatus.OK);
    }

    @Test
    public void testCleanCart() {
        Cart clearedCart = vaneasCart.cleanCart();
        CartDto clearedCartDto = cartMapper.toCartDto(clearedCart);

        when(cartService.clearCart(vanea.id())).thenReturn(clearedCart);

        ResponseEntity<CartDto> response = controller.cleanCart(vanea.id());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clearedCartDto.cartItemDtos(), List.of());
    }

    private void assertUpdatedCart(
            CartDto expectedCartDto,
            ResponseEntity<CartDto> response,
            HttpStatus expectedHttpStatus) {

        final CartDto responseBody = response.getBody();

        assertEquals(expectedHttpStatus, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(expectedCartDto, responseBody);
        assertEquals(expectedCartDto.status(), responseBody.status());
        assertEquals(expectedCartDto.clientDto(), responseBody.clientDto());
        assertEquals(expectedCartDto.clientDto().firstName(), responseBody.clientDto().firstName());
        assertEquals(expectedCartDto.clientDto().lastName(), responseBody.clientDto().lastName());
        assertEquals(expectedCartDto.cartItemDtos(), responseBody.cartItemDtos());
        assertEquals(expectedCartDto.cartItemDtos().getFirst(), responseBody.cartItemDtos().getFirst());
        assertEquals(expectedCartDto.cartItemDtos().getLast(), responseBody.cartItemDtos().getLast());
        assertEquals(expectedCartDto.cartItemDtos().getFirst().quantity(), responseBody.cartItemDtos().getFirst().quantity());
        assertEquals(expectedCartDto.cartItemDtos().getLast().quantity(), responseBody.cartItemDtos().getLast().quantity());
        assertEquals(expectedCartDto.cartItemDtos().getFirst().productDto().name(), responseBody.cartItemDtos().getFirst().productDto().name());
        assertEquals(expectedCartDto.cartItemDtos().getLast().productDto().name(), responseBody.cartItemDtos().getLast().productDto().name());
    }
}
