package in.mapper;

import in.dto.cart.CartDto;
import in.dto.cart.CartItemDto;
import model.cart.Cart;
import model.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper interface for converting between {@link Cart}, {@link CartItem}
 * and their corresponding DTOs.
 * Handles transformations between domain and API layers.
 */
@Mapper
public interface CartMapper {

    /**
     * Maps a {@link Cart} domain object to a {@link CartDto}.
     *
     * @param cart the domain cart object
     * @return the mapped {@link CartDto}
     */
    @Mapping(target = "clientDto", source = "client")
    @Mapping(target = "cartItemDtos", source = "cartItems")
    CartDto toCartDto(Cart cart);

    /**
     * Maps a {@link CartDto} back to a {@link Cart} domain object.
     * Ignores the cart ID during mapping.
     *
     * @param cartDto the DTO to convert
     * @return the mapped {@link Cart}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "clientDto")
    @Mapping(target = "cartItems", source = "cartItemDtos")
    Cart toCart(CartDto cartDto);

    /**
     * Maps a {@link CartItem} domain object to a {@link CartItemDto}.
     *
     * @param cartItem the domain cart item
     * @return the mapped {@link CartItemDto}
     */
    @Mapping(target = "productDto", source = "product")
    CartItemDto toCartItemDto(CartItem cartItem);

    /**
     * Maps a {@link CartItemDto} back to a {@link CartItem}.
     * Ignores dynamic quantity operations (handled in service logic).
     *
     * @param cartItemDto the DTO to convert
     * @return the mapped {@link CartItem}
     */
    @Mapping(target = "product", source = "productDto")
    @Mapping(target = "increaseQuantity", ignore = true)
    @Mapping(target = "decreaseQuantity", ignore = true)
    CartItem toCartItem(CartItemDto cartItemDto);
}
