package adapter.out.mapper;

import model.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.restaurant.RestaurantEntity;
import out.mapper.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static adapter.Fixtures.laPlacinte;

@ContextConfiguration(classes = {
        RestaurantEntityMapperImpl.class,
        UserAccountEntityMapperImpl.class,
        BankingDetailsEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class RestaurantEntityMapperTest {

    @Autowired private RestaurantEntityMapper restaurantEntityMapper;

    @Test
    public void testMapToRestaurantEntity() {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(laPlacinte);

        assertEquals(restaurantEntity.getName(), laPlacinte.name());
        assertEquals(restaurantEntity.getAddress(), laPlacinte.location().address().value());
        assertEquals(restaurantEntity.getLatitude(), laPlacinte.location().latitude());
        assertEquals(restaurantEntity.getLongitude(), laPlacinte.location().longitude());
        assertEquals(restaurantEntity.getUserAccount().getEmail(), laPlacinte.userAccount().email().value());
        assertEquals(restaurantEntity.getUserAccount().getRole(), laPlacinte.userAccount().role());
        assertEquals(restaurantEntity.getDescription(), laPlacinte.description());
    }

    @Test
    public void testMapEntityToRestaurant() {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(laPlacinte);
        Restaurant restaurant = restaurantEntityMapper.toRestaurant(restaurantEntity);

        assertEquals(restaurant.name(), laPlacinte.name());
        assertEquals(restaurant.location().address().value(), laPlacinte.location().address().value());
        assertEquals(restaurant.location().latitude(), laPlacinte.location().latitude());
        assertEquals(restaurant.location().longitude(), laPlacinte.location().longitude());
        assertEquals(restaurant.userAccount().email().value(), laPlacinte.userAccount().email().value());
        assertEquals(restaurant.userAccount().role(), laPlacinte.userAccount().role());
        assertEquals(restaurant.description(), laPlacinte.description());
    }
}
