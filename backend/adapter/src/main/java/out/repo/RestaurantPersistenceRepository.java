package out.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import out.entity.restaurant.RestaurantEntity;

@Repository
public interface RestaurantPersistenceRepository extends JpaRepository<RestaurantEntity, Long> {
}
