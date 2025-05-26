package out.mapper;

import model.cart.Cart;
import org.mapstruct.Mapper;
import out.entity.cart.CartEntity;

@Mapper(componentModel = "spring", uses = {
    UserAccountEntityMapper.class, 
    CartItemEntityMapper.class})
public interface CartEntityMapper {

    CartEntity toCartEntity(Cart cart);

    Cart toCart(CartEntity cartEntity);
}
