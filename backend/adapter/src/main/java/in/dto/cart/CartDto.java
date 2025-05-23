package in.dto.cart;

import in.dto.client.ClientDto;
import lombok.Builder;
import lombok.NonNull;
import model.cart.Status;

import java.util.List;

/**
 * Data transfer object representing the contents of a shopping cart.
 * Used in API responses to expose cart state including the client, cart status, and items.
 *
 * @param clientDto the client who owns the cart
 * @param status the current status of the cart (e.g., ACTIVE, CHECKED_OUT)
 * @param cartItemDtos the list of items currently in the cart
 */
@Builder(toBuilder = true)
public record CartDto(
        @NonNull ClientDto clientDto,
        @NonNull Status status,
        @NonNull List<CartItemDto> cartItemDtos) {
}
