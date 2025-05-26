package out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import model.restaurant.Restaurant;
import org.springframework.stereotype.Component;
import out.RestaurantRepository;
import out.entity.restaurant.RestaurantEntity;
import out.mapper.RestaurantEntityMapper;
import out.repo.RestaurantPersistenceRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RestaurantPersistence implements RestaurantRepository {

    private RestaurantPersistenceRepository restaurantPersistenceRepository;
    private RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Optional<Restaurant> findById(long restaurantId) {
        return restaurantPersistenceRepository.findById(restaurantId)
                .map(restaurantEntityMapper::toRestaurant);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurant);
        RestaurantEntity savedRestaurantEntity = restaurantPersistenceRepository.save(restaurantEntity);

        return restaurantEntityMapper.toRestaurant(savedRestaurantEntity);
    }

    @Override
    public void deleteById(long restaurantId) {
        if (!restaurantPersistenceRepository.existsById(restaurantId))
            throw new EntityNotFoundException("Restaurant not found");

        restaurantPersistenceRepository.deleteById(restaurantId);
    }
}
