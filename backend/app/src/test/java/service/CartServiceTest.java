package service;

import model.cart.Cart;
import model.cart.CartItem;
import model.restaurant.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.CartRepository;
import out.ClientRepository;
import out.ProductRepository;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static service.Fixtures.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock private CartRepository cartRepository;
    @Mock private ClientRepository clientRepository;
    @Mock private ProductRepository productRepository;
    @InjectMocks private CartService cartService;

    @Test
    public void testGetCurrentCart() {
        when(cartRepository.findByClientId(vanea.id())).thenReturn(Optional.of(vaneasCart));
        Cart expectedCart = cartService.getCurrentCart(vanea.id());

        assertEquals(vaneasCart, expectedCart);
    }

    @Test
    public void testAddItemToCart() {
        Cart expectedCart = vaneasCart.addItem(chifla, 1);

        when(clientRepository.findById(vanea.id())).thenReturn(Optional.of(vanea));
        when(productRepository.findById(chifla.id())).thenReturn(Optional.of(chifla));
        when(cartRepository.findByClientId(vanea.id())).thenReturn(Optional.of(vaneasCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

        Cart updatedCart = cartService.addItemToCart(vanea.id(), chifla.id(), 1);

        assertEquals(expectedCart.cartItems(), updatedCart.cartItems());
    }

    @Test
    public void testRemoveItemFromCart() {
        Cart expectedCart = vaneasCart.removeItem(placinte);

        when(productRepository.findById(reservedPlacinte.id())).thenReturn(Optional.of(reservedPlacinte));
        when(cartRepository.findByClientId(vanea.id())).thenReturn(Optional.of(vaneasCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

        Cart updatedCart = cartService.removeItemFromCart(vanea.id(), reservedPlacinte.id());

        assertEquals(expectedCart.cartItems(), updatedCart.cartItems());
    }

    @Test
    public void testDecreaseItemQuantity() {
        final int newQuantity = 2;
        final int quantityToRelease = 1;

        Product reducedPlacinte = reservedPlacinte.reduce(quantityToRelease);
        CartItem updatedPlacinte= new CartItem(reducedPlacinte, newQuantity);

        Cart expectedCart = vaneasCart.toBuilder()
                .cartItems(List.of(jumeriInCart, updatedPlacinte))
                .build();

        when(productRepository.findById(reservedPlacinte.id())).thenReturn(Optional.of(reservedPlacinte));
        when(cartRepository.findByClientId(vanea.id())).thenReturn(Optional.of(vaneasCart));
        when(productRepository.save(any(Product.class))).thenReturn(reducedPlacinte);
        when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

        Cart updatedCart = cartService.decreaseItemQuantity(vanea.id(), reservedPlacinte.id(), quantityToRelease);

        assertEquals(2, updatedCart.cartItems().size());
        assertEquals(newQuantity, updatedCart.cartItems().getLast().quantity());
        assertEquals(reducedPlacinte, updatedCart.cartItems().getLast().product());
    }

    @Test
    public void testIncreaseItemQuantity() {
        final int newQuantity = 4;
        final int quantityToIncrease = 1;

        Product increasedPlacinte = reservedPlacinte.reserve(quantityToIncrease);
        CartItem updatedPlacinte = new CartItem(increasedPlacinte, newQuantity);

        Cart expectedCart = vaneasCart.toBuilder()
                .cartItems(List.of(jumeriInCart, updatedPlacinte))
                .build();

        when(productRepository.findById(reservedPlacinte.id())).thenReturn(Optional.of(reservedPlacinte));
        when(cartRepository.findByClientId(vanea.id())).thenReturn(Optional.of(vaneasCart));
        when(productRepository.save(any(Product.class))).thenReturn(increasedPlacinte);
        when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

        Cart updatedCart = cartService.decreaseItemQuantity(vanea.id(), reservedPlacinte.id(), quantityToIncrease);

        assertEquals(2, updatedCart.cartItems().size());
        assertEquals(newQuantity, updatedCart.cartItems().getLast().quantity());
        assertEquals(increasedPlacinte, updatedCart.cartItems().getLast().product());
    }

}
