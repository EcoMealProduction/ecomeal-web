package in.rest;

import in.dto.cart.CartDto;
import in.mapper.CartMapper;
import model.cart.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CartService;

/**
 * REST controller for managing a client's shopping cart.
 * Handles adding, updating, removing, and retrieving cart items.
 */
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    /**
     * Adds a product to the client's cart with a specified quantity.
     *
     * @param clientId the ID of the client
     * @param productId the ID of the product
     * @param quantity number of units to add
     * @return the updated cart as {@link CartDto}
     */
    @PostMapping("/{clientId}/{productId}")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable long clientId,
            @PathVariable long productId,
            @RequestParam int quantity) {

        Cart cart = cartService.addItemToCart(clientId, productId, quantity);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return new ResponseEntity<>(cartDto ,HttpStatus.CREATED);
    }

    /**
     * Removes a product entirely from the client's cart.
     *
     * @param clientId the ID of the client
     * @param productId the ID of the product to remove
     * @return the updated cart as {@link CartDto}
     */
    @DeleteMapping("/{clientId}/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(
            @PathVariable long clientId,
            @PathVariable long productId) {
        Cart cart = cartService.removeItemFromCart(clientId, productId);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return ResponseEntity.ok(cartDto);
    }

    /**
     * Decreases the quantity of a product in the cart.
     *
     * @param clientId the ID of the client
     * @param productId the ID of the product
     * @param quantity amount to decrease
     * @return the updated cart as {@link CartDto}
     */
    @PatchMapping("/{clientId}/{productId}/quantity/decrease")
    public ResponseEntity<CartDto> decreaseItemQuantity(
            @PathVariable long clientId,
            @PathVariable long productId,
            @RequestParam int quantity) {

        Cart cart = cartService.decreaseItemQuantity(clientId, productId, quantity);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return ResponseEntity.ok(cartDto);
    }

    /**
     * Increases the quantity of a product in the cart.
     *
     * @param clientId the ID of the client
     * @param productId the ID of the product
     * @param quantity amount to increase
     * @return the updated cart as {@link CartDto}
     */
    @PatchMapping("/{clientId}/{productId}/quantity/increase")
    public ResponseEntity<CartDto> increaseItemQuantity(
            @PathVariable long clientId,
            @PathVariable long productId,
            @RequestParam int quantity) {

        Cart cart = cartService.increaseItemQuantity(clientId, productId, quantity);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return ResponseEntity.ok(cartDto);
    }

    /**
     * Retrieves the current contents of the client's cart.
     *
     * @param clientId the ID of the client
     * @return the current cart as {@link CartDto}
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<CartDto> getCurrentCart(@PathVariable long clientId) {
        Cart cart = cartService.getCurrentCart(clientId);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return ResponseEntity.ok(cartDto);
    }

    /**
     * Clears all items from the client's cart.
     *
     * @param clientId the ID of the client
     * @return the emptied cart as {@link CartDto}
     */
    @DeleteMapping("/{clientId}/items")
    public ResponseEntity<CartDto> cleanCart(@PathVariable long clientId) {
        Cart cart = cartService.clearCart(clientId);
        CartDto cartDto = cartMapper.toCartDto(cart);

        return ResponseEntity.ok(cartDto);
    }
}
