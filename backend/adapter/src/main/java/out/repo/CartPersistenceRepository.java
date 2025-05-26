package out.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import out.entity.cart.CartEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CartPersistenceRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByClientId(long clientId);

    @Modifying
    void expireInactiveCarts(@Param("threshold") LocalDateTime threshold);
}
