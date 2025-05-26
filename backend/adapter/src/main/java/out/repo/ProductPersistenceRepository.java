package out.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import out.entity.restaurant.ProductEntity;

import java.time.OffsetTime;
import java.util.List;

@Repository
public interface ProductPersistenceRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByRestaurantId(long restaurantId);

    @Query("""
        SELECT p FROM ProductEntity p
        WHERE p.totalQuantity > p.reservedQuantity
        AND p.pickUpTime > :now
    """)
    Page<ProductEntity> findAvailableProducts(@Param("now") OffsetTime now, Pageable pageable);
}
