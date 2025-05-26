package model;

import model.cart.CartItem;
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
import java.time.OffsetTime;
import java.util.Currency;

public final class Fixtures {
    public static final Email email = new Email("vasea1@gmail.com");
    public static final PhoneNumber phoneNumber = new PhoneNumber("+37360724176");
    public static final Password password = new Password("Vasea123!");
    public static final UserAccount clientUserAccount = new UserAccount(1L, email, phoneNumber, password, Role.CLIENT);
    public static final Country moldova = new Country("Moldova", "MD");
    public static final BankingDetails bankingDetails = new BankingDetails(
            1L, "da", moldova, "123", "da", "da" );

    public static final Address aachenerNobisAddress = new Address("Münsterpl. 3, 52062 Aachen");
    public static final BigDecimal aachenerNobisLatitude = BigDecimal.valueOf(50.774640969053024);
    public static final BigDecimal aachenerNobisLongitude = BigDecimal.valueOf(6.08490228561747);

    public static final Address aachenerDomAddress = new Address("Domhof 1, 52062 Aachen");
    public static final BigDecimal aachenerDomLatitude = BigDecimal.valueOf(50.77473538509855);
    public static final BigDecimal aachenerDomLongitude = BigDecimal.valueOf(6.083920847968691);

    public static final Address albertHeijnAddress = new Address(
            "Prins Willem Alexanderplein 5, 6291 GZ Vaals, Niederlande");
    public static final BigDecimal albertHeijnLatitude = BigDecimal.valueOf(50.772877629426176);
    public static final BigDecimal albertHeijnLongitude = BigDecimal.valueOf(6.0209963286457375);

    public static final Location aachenerNobis = new Location(
            aachenerNobisAddress, aachenerNobisLatitude, aachenerNobisLongitude);
    public static final Location aachenerDom = new Location(
            aachenerDomAddress, aachenerDomLatitude, aachenerDomLongitude);
    public static final Location albertHeijn = new Location(
            albertHeijnAddress, albertHeijnLatitude, albertHeijnLongitude);

    public static final UserAccount restaurantUserAccount = new UserAccount(1L, email, phoneNumber, password, Role.RESTAURANT);
    public static final Restaurant andys = Restaurant.builder()
            .id(1L)
            .userAccount(restaurantUserAccount)
            .name("Andys")
            .location(aachenerNobis)
            .bankingDetails(bankingDetails)
            .build();

    public static final Currency eur = Currency.getInstance("EUR");
    public static final Money bakshish = new Money(eur, new BigDecimal(12));
    public static final OffsetTime pickUpTime = OffsetTime.now();

    public static final Product plashinti = Product.builder()
            .id(1L)
            .name("Plashinti")
            .description("gustoase ca la matta")
            .totalQuantity(10)
            .restaurant(andys)
            .price(bakshish)
            .pickUpTime(pickUpTime)
            .build();

    public static final Product jumeri = Product.builder()
            .id(2L)
            .name("Jumeri")
            .description("gustoase ca la tattu")
            .totalQuantity(10)
            .restaurant(andys)
            .price(bakshish)
            .pickUpTime(pickUpTime)
            .build();

    public static final Product chifla = Product.builder()
            .id(3L)
            .name("Chifla")
            .description("moale ca bunicta")
            .totalQuantity(15)
            .restaurant(andys)
            .price(bakshish)
            .pickUpTime(pickUpTime)
            .build();

    public static final CartItem plashintiInCart = new CartItem(plashinti, 3);
    public static final CartItem jumeriInCart = new CartItem(jumeri, 5);
    public static final CartItem chiflaInCart = new CartItem(chifla, 3);

    public static final Client vanea = Client.builder()
            .id(1L)
            .firstName("vanea")
            .lastName("sifon")
            .userAccount(clientUserAccount)
            .country(moldova)
            .location(aachenerDom)
            .bankingDetails(bankingDetails)
            .pickUpRadiusKm(12)
            .build();

}
