package out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import model.cart.Cart;
import org.springframework.stereotype.Component;
import out.CartRepository;
import out.entity.cart.CartEntity;
import out.mapper.CartEntityMapper;
import out.repo.CartPersistenceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CartPersistence implements CartRepository {

    private CartPersistenceRepository cartPersistenceRepository;
    private CartEntityMapper cartEntityMapper;

    @Override
    public Optional<Cart> findByClientId(long clientId) {
        return cartPersistenceRepository.findByClientId(clientId)
                .map(cartEntityMapper::toCart);
    }

    @Override
    public Cart save(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toCartEntity(cart);
        CartEntity savedCartEntity = cartPersistenceRepository.save(cartEntity);

        return cartEntityMapper.toCart(savedCartEntity);
    }

    @Override
    public void deleteById(long cartId) {
        if (!cartPersistenceRepository.existsById(cartId))
            throw new EntityNotFoundException("Cart not found");

        cartPersistenceRepository.deleteById(cartId);
    }

    @Override
    public void expireCartsInactiveForMinutes(int minutes) {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(minutes);

        cartPersistenceRepository.expireInactiveCarts(threshold);
    }
}
