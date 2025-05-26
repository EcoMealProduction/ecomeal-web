package out.mapper;

import model.restaurant.Restaurant;
import model.shared.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import out.entity.restaurant.RestaurantEntity;

@Mapper(componentModel = "spring",
        uses = {
        UserAccountEntityMapper.class,
        BankingDetailsEntityMapper.class})
public interface RestaurantEntityMapper {

    @Mapping(source = "location.address", target = "address", qualifiedByName = "entityToString")
    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.longitude", target = "longitude")
    RestaurantEntity toRestaurantEntity(Restaurant restaurant);

    @Mapping(target = "location.address", source = "address", qualifiedByName = "toAddress")
    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    @Mapping(target = "id", ignore = true)
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    @Named("entityToString")
    default String entityToString(Address address) {
        return address.value();
    }

    @Named("toAddress")
    default Address toAddress(String value) {
        return new Address(value);
    }

}
