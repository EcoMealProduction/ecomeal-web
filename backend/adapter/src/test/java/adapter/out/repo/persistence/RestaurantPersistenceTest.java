package adapter.out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import model.restaurant.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.entity.restaurant.RestaurantEntity;
import out.mapper.RestaurantEntityMapper;
import out.repo.RestaurantPersistenceRepository;
import out.repo.persistence.RestaurantPersistence;

import java.util.Optional;

import static adapter.Fixtures.laPlacinte;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantPersistenceTest {

    @Mock private RestaurantPersistenceRepository restaurantPersistenceRepository;

    @Mock private RestaurantEntityMapper restaurantEntityMapper;

    @InjectMocks private RestaurantPersistence restaurantPersistence;

    @Test
    public void findById_returnsMappedRestaurant_whenFound() {
        long id = laPlacinte.id();
        RestaurantEntity entity = mock(RestaurantEntity.class);

        when(restaurantPersistenceRepository.findById(id)).thenReturn(Optional.of(entity));
        when(restaurantEntityMapper.toRestaurant(entity)).thenReturn(laPlacinte);

        Optional<Restaurant> result = restaurantPersistence.findById(id);

        assertTrue(result.isPresent());
        assertEquals(laPlacinte, result.get());
    }

    @Test
    public void findById_returnsEmpty_whenNotFound() {
        when(restaurantPersistenceRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Restaurant> result = restaurantPersistence.findById(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    public void save_mapsAndSavesRestaurant() {
        RestaurantEntity toSave = mock(RestaurantEntity.class);
        RestaurantEntity saved = mock(RestaurantEntity.class);

        when(restaurantEntityMapper.toRestaurantEntity(laPlacinte)).thenReturn(toSave);
        when(restaurantPersistenceRepository.save(toSave)).thenReturn(saved);
        when(restaurantEntityMapper.toRestaurant(saved)).thenReturn(laPlacinte);

        Restaurant result = restaurantPersistence.save(laPlacinte);

        assertEquals(laPlacinte, result);
    }

    @Test
    public void deleteById_deletes_whenExists() {
        long id = laPlacinte.id();
        when(restaurantPersistenceRepository.existsById(id)).thenReturn(true);

        restaurantPersistence.deleteById(id);
    }

    @Test
    public void deleteById_throws_whenNotExists() {
        long id = 404L;
        when(restaurantPersistenceRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> restaurantPersistence.deleteById(id));
    }
}
