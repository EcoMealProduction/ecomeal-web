package adapter.out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import model.restaurant.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import out.entity.restaurant.ProductEntity;
import out.mapper.ProductEntityMapper;
import out.repo.ProductPersistenceRepository;
import out.repo.persistence.ProductPersistence;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

import static adapter.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductPersistenceTest {

    @Mock private ProductPersistenceRepository productPersistenceRepository;

    @Mock private ProductEntityMapper productEntityMapper;

    @InjectMocks private ProductPersistence productPersistence;

    @Test
    public void findById_returnsMappedProduct_whenFound() {
        long id = placinte.id();
        ProductEntity entity = mock(ProductEntity.class);

        when(productPersistenceRepository.findById(id)).thenReturn(Optional.of(entity));
        when(productEntityMapper.toProduct(entity)).thenReturn(placinte);

        Optional<Product> result = productPersistence.findById(id);

        assertTrue(result.isPresent());
        assertEquals(placinte, result.get());
        verify(productPersistenceRepository).findById(id);
        verify(productEntityMapper).toProduct(entity);
    }

    @Test
    public void findById_returnsEmpty_whenNotFound() {
        when(productPersistenceRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Product> result = productPersistence.findById(999L);

        assertTrue(result.isEmpty());
        verify(productPersistenceRepository).findById(999L);
        verifyNoInteractions(productEntityMapper);
    }

    @Test
    public void findAllByRestaurantId_returnsMappedProducts() {
        long restaurantId = laPlacinte.id();
        ProductEntity entity1 = mock(ProductEntity.class);
        ProductEntity entity2 = mock(ProductEntity.class);

        when(productPersistenceRepository.findAllByRestaurantId(restaurantId)).thenReturn(List.of(entity1, entity2));
        when(productEntityMapper.toProduct(entity1)).thenReturn(placinte);
        when(productEntityMapper.toProduct(entity2)).thenReturn(jumeri);

        List<Product> result = productPersistence.findAllByRestaurantId(restaurantId);

        assertEquals(List.of(placinte, jumeri), result);
        verify(productPersistenceRepository).findAllByRestaurantId(restaurantId);
    }

    @Test
    public void save_persistsMappedProduct() {
        ProductEntity toSave = mock(ProductEntity.class);
        ProductEntity saved = mock(ProductEntity.class);

        when(productEntityMapper.toProductEntity(placinte)).thenReturn(toSave);
        when(productPersistenceRepository.save(toSave)).thenReturn(saved);
        when(productEntityMapper.toProduct(saved)).thenReturn(placinte);

        Product result = productPersistence.save(placinte);

        assertEquals(placinte, result);
        verify(productEntityMapper).toProductEntity(placinte);
        verify(productPersistenceRepository).save(toSave);
        verify(productEntityMapper).toProduct(saved);
    }

    @Test
    public void deleteById_deletes_whenExists() {
        long id = placinte.id();
        when(productPersistenceRepository.existsById(id)).thenReturn(true);

        productPersistence.deleteById(id);

        verify(productPersistenceRepository).existsById(id);
        verify(productPersistenceRepository).deleteById(id);
    }

    @Test
    public void deleteById_throws_whenNotExists() {
        long id = 999L;
        when(productPersistenceRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> productPersistence.deleteById(id));
        verify(productPersistenceRepository).existsById(id);
        verify(productPersistenceRepository, never()).deleteById(anyLong());
    }

    @Test
    public void findAvailableProducts_mapsPagedProducts() {
        PageRequest request = PageRequest.of(0, 5);
        ProductEntity entity = mock(ProductEntity.class);
        Page<ProductEntity> entityPage = new PageImpl<>(List.of(entity));
        Page<Product> domainPage = new PageImpl<>(List.of(placinte));

        when(productPersistenceRepository.findAvailableProducts(any(), eq(request)))
                .thenReturn(entityPage);
        when(productEntityMapper.toProduct(entity)).thenReturn(placinte);

        Page<Product> result = productPersistence.findAvailableProducts(request);

        assertEquals(domainPage.getContent(), result.getContent());
        verify(productPersistenceRepository).findAvailableProducts(any(), eq(request));
        verify(productEntityMapper).toProduct(entity);
    }
}
