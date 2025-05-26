package out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import model.restaurant.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import out.ProductRepository;
import out.entity.restaurant.ProductEntity;
import out.mapper.ProductEntityMapper;
import out.repo.ProductPersistenceRepository;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductPersistence implements ProductRepository {

    private ProductPersistenceRepository productPersistenceRepository;
    private ProductEntityMapper productEntityMapper;

    @Override
    public Optional<Product> findById(long productId) {
        return productPersistenceRepository.findById(productId)
                .map(productEntityMapper::toProduct);
    }

    @Override
    public List<Product> findAllByRestaurantId(long restaurantId) {
        return productPersistenceRepository.findAllByRestaurantId(restaurantId)
                .stream()
                .map(productEntityMapper::toProduct)
                .toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productEntityMapper.toProductEntity(product);
        ProductEntity savedProductEntity = productPersistenceRepository.save(productEntity);

        return productEntityMapper.toProduct(savedProductEntity);
    }

    @Override
    public void deleteById(long productId) {
        if (!productPersistenceRepository.existsById(productId))
            throw new EntityNotFoundException("Product not found");

        productPersistenceRepository.deleteById(productId);
    }

    @Override
    public Page<Product> findAvailableProducts(PageRequest pageRequest) {
        OffsetTime now = OffsetTime.now();
        Page<ProductEntity> page = productPersistenceRepository.findAvailableProducts(now, pageRequest);

        return page.map(productEntityMapper::toProduct);
    }
}
