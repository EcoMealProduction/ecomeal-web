package adapter.out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import model.cart.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.entity.cart.CartEntity;
import out.mapper.CartEntityMapper;
import out.repo.CartPersistenceRepository;
import out.repo.persistence.CartPersistence;

import java.util.Optional;

import static adapter.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartPersistenceTest {

    @Mock private CartPersistenceRepository cartPersistenceRepository;
    @Mock private CartEntityMapper cartEntityMapper;
    @InjectMocks private CartPersistence cartPersistence;

    private CartEntity cartEntity;

    @BeforeEach
    public void setup() {
        cartEntity = mock(CartEntity.class);
    }

    @Test
    public void testFindClientById() {
        when(cartPersistenceRepository.findByClientId(vanea.id()))
                .thenReturn(Optional.of(cartEntity));
        when(cartEntityMapper.toCart(cartEntity)).thenReturn(vaneasCart);

        Optional<Cart> result = cartPersistence.findByClientId(vanea.id());

        assertTrue(result.isPresent());
        assertEquals(vaneasCart, result.get());
    }

    @Test
    public void testSaveCart() {
        CartEntity savedCartEntity = mock(CartEntity.class);

        when(cartEntityMapper.toCartEntity(vaneasCart)).thenReturn(cartEntity);
        when(cartPersistenceRepository.save(cartEntity)).thenReturn(savedCartEntity);
        when(cartEntityMapper.toCart(savedCartEntity)).thenReturn(vaneasCart);

        Cart result = cartPersistence.save(vaneasCart);

        assertEquals(vaneasCart, result);
    }

    @Test
    public void testDeleteCartById() {
        when(cartPersistenceRepository.existsById(vaneasCart.id())).thenReturn(true);
        cartPersistence.deleteById(vaneasCart.id());
        verify(cartPersistenceRepository, times(1)).deleteById(vaneasCart.id());
    }

    @Test
    public void testCartNotFound() {
        when(cartPersistenceRepository.existsById(vaneasCart.id())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> cartPersistence.deleteById(vaneasCart.id()));
    }
}
