package out.mapper;

import org.mapstruct.Mapper;

import model.cart.CartItem;
import org.mapstruct.Mapping;
import out.entity.cart.CartItemEntity;

@Mapper(componentModel = "spring", uses = {
        UserAccountEntityMapper.class,
        ProductEntityMapper.class})
public interface CartItemEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    CartItemEntity toCartItemEntity(CartItem cartItem);

    @Mapping(target = "increaseQuantity", ignore = true)
    @Mapping(target = "decreaseQuantity", ignore = true)
    CartItem toCartItem(CartItemEntity cartItemEntity);
}
