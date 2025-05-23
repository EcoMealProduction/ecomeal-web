package in.mapper;

import in.dto.client.ClientDto;
import in.dto.restaurant.LocationDto;
import in.dto.restaurant.RestaurantDto;
import in.dto.shared.AddressDto;
import in.dto.user.UserAccountDto;
import model.client.Client;
import model.restaurant.Location;
import model.restaurant.Restaurant;
import model.shared.Address;
import model.user.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper for converting between user-related domain objects and their DTO representations.
 * Supports mapping for accounts, clients, restaurants, locations, and addresses.
 */
@Mapper
public interface UserMapper {

    /**
     * Maps a {@link UserAccount} domain object to a {@link UserAccountDto}.
     *
     * @param userAccount the domain user account
     * @return the corresponding {@link UserAccountDto}
     */
    @Mapping(target = "passwordDto", source = "password")
    @Mapping(target = "phoneNumberDto", source = "phoneNumber")
    @Mapping(target = "emailDto", source = "email")
    UserAccountDto toUserAccountDto(UserAccount userAccount);

    /**
     * Maps a {@link UserAccountDto} back to a {@link UserAccount} domain object.
     *
     * @param userAccountDto the DTO to convert
     * @return the corresponding {@link UserAccount}
     */
    @Mapping(target = "phoneNumber", source = "phoneNumberDto")
    @Mapping(target = "password", source = "passwordDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "emailDto")
    UserAccount toUserAccount(UserAccountDto userAccountDto);

    /**
     * Maps a {@link Client} domain object to a {@link ClientDto}.
     *
     * @param client the domain client
     * @return the corresponding {@link ClientDto}
     */
    @Mapping(target = "userAccountDto", source = "userAccount")
    @Mapping(target = "locationDto", source = "location")
    @Mapping(target = "bankingDetailsDto", source = "bankingDetails")
    ClientDto toClientDto(Client client);

    /**
     * Maps a {@link ClientDto} back to a {@link Client} domain object.
     *
     * @param clientDto the DTO to convert
     * @return the corresponding {@link Client}
     */
    @Mapping(target = "userAccount", source = "userAccountDto")
    @Mapping(target = "location", source = "locationDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankingDetails", source = "bankingDetailsDto")
    Client toClient(ClientDto clientDto);

    /**
     * Maps a {@link Restaurant} domain object to a {@link RestaurantDto}.
     *
     * @param restaurant the domain restaurant
     * @return the corresponding {@link RestaurantDto}
     */
    @Mapping(target = "userAccountDto", source = "userAccount")
    @Mapping(target = "locationDto", source = "location")
    @Mapping(target = "bankingDetailsDto", source = "bankingDetails")
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    /**
     * Maps a {@link RestaurantDto} back to a {@link Restaurant} domain object.
     *
     * @param restaurantDto the DTO to convert
     * @return the corresponding {@link Restaurant}
     */
    @Mapping(target = "userAccount", source = "userAccountDto")
    @Mapping(target = "location", source = "locationDto")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bankingDetails", source = "bankingDetailsDto")
    Restaurant toRestaurant(RestaurantDto restaurantDto);

    /**
     * Maps an {@link Address} to an {@link AddressDto}.
     *
     * @param address the domain address
     * @return the corresponding {@link AddressDto}
     */
    AddressDto toAddressDto(Address address);

    /**
     * Maps an {@link AddressDto} back to an {@link Address}.
     *
     * @param addressDto the DTO to convert
     * @return the corresponding {@link Address}
     */
    Address toAddress(AddressDto addressDto);

    /**
     * Maps a {@link Location} domain object to a {@link LocationDto}.
     *
     * @param location the domain location
     * @return the corresponding {@link LocationDto}
     */
    @Mapping(target = "addressDto", source = "address")
    LocationDto toLocationDto(Location location);

    /**
     * Maps a {@link LocationDto} back to a {@link Location} domain object.
     *
     * @param locationDto the DTO to convert
     * @return the corresponding {@link Location}
     */
    @Mapping(target = "address", source = "addressDto")
    Location toLocation(LocationDto locationDto);
}
