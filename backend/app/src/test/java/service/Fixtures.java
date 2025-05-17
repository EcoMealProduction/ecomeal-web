package service;

import model.cart.Cart;
import model.cart.CartItem;
import model.cart.Status;
import model.client.Client;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Product;
import model.restaurant.Restaurant;
import model.shared.*;
import model.shared.password.Password;
import model.user.Role;
import model.user.UserAccount;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;

public final class Fixtures {

    public static final Country moldova = new Country("Moldova", "MD");
    public static final BankingDetails bankingDetails = new BankingDetails(
            1L, "MAIB", moldova, "MD12334", "Chisinau", "str. cuza voda 12");

    /**
     * Product price details
     */
    public static final Currency mdl = Currency.getInstance("MDL");
    public static final Money lei = new Money(mdl, new BigDecimal(12));
    public static final OffsetDateTime pickUpTime = OffsetDateTime.now().plusHours(3);

    /**
     * Client personal data
     */
    public static final Email clientEmail = new Email("email@gmail.com");
    public static final PhoneNumber clientPhoneNumber = new PhoneNumber("+37365434554");
    public static final Password clientPassword = new Password("VaneaCaciok1!");
    public static final UserAccount vaneaUserAccount = UserAccount.builder()
            .id(1L)
            .email(clientEmail)
            .phoneNumber(clientPhoneNumber)
            .password(clientPassword)
            .role(Role.CLIENT)
            .build();

    public static final Client vanea = Client.builder()
            .id(1L)
            .firstName("Vanea")
            .lastName("Sandala")
            .userAccount(vaneaUserAccount)
            .country(moldova)
            .bankingDetails(bankingDetails)
            .pickUpRadiusKm(12)
            .build();

    /**
     * Restaurant personal data
     */
    public static final Email restaurantEmail = new Email("laPlacinte@gmail.com");
    public static final PhoneNumber restaurantPhoneNumber = new PhoneNumber("+37360724176");
    public static final Password restaurantPassword = new Password("laPlacinte123!");
    public static final UserAccount laPlacinteUserAccount = UserAccount.builder()
            .id(1L)
            .email(restaurantEmail)
            .phoneNumber(restaurantPhoneNumber)
            .password(restaurantPassword)
            .role(Role.RESTAURANT)
            .build();

    public static final Address laPlacinteAddress = new Address("bl. Dacia 111");
    public static final BigDecimal laPlacinteLatitude = BigDecimal.valueOf(50.774640969053024);
    public static final BigDecimal laPlacinteLongitude = BigDecimal.valueOf(6.08490228561747);
    public static final Location laPlacinteLocation = new Location(
            laPlacinteAddress, laPlacinteLatitude, laPlacinteLongitude);

    public static final Restaurant laPlacinte = Restaurant.builder()
            .id(1L)
            .userAccount(laPlacinteUserAccount)
            .name("La Placinte")
            .description("gustoase placintele")
            .location(laPlacinteLocation)
            .bankingDetails(bankingDetails)
            .build();

    /**
     * Products
     */
    public static final Product placinte = Product.builder()
            .id(1L)
            .name("placinte")
            .description("gustoase ca la matta")
            .totalQuantity(10)
            .price(lei)
            .pickUpTime(pickUpTime)
            .build();

    public static final Product jumeri = Product.builder()
            .id(2L)
            .name("Jumeri")
            .description("gustoase ca la tattu")
            .totalQuantity(10)
            .restaurant(laPlacinte)
            .price(lei)
            .pickUpTime(pickUpTime)
            .build();

    public static final Product chifla = Product.builder()
            .id(3L)
            .name("Chifla")
            .description("moale ca bunicta")
            .totalQuantity(15)
            .restaurant(laPlacinte)
            .price(lei)
            .pickUpTime(pickUpTime)
            .build();

    public static final Product reservedPlacinte = placinte.reserve(3);
    public static final Product reservedJumeri = jumeri.reserve(5);

    public static final CartItem placinteInCart = new CartItem(reservedPlacinte, 3);
    public static final CartItem jumeriInCart = new CartItem(reservedJumeri, 5);
    public static final CartItem chiflaInCart = new CartItem(chifla.reserve(3), 3);

    public static final Cart vaneasCart = Cart.builder()
            .client(vanea)
            .cartItems(List.of(placinteInCart, jumeriInCart))
            .status(Status.ACTIVE)
            .build();
}
