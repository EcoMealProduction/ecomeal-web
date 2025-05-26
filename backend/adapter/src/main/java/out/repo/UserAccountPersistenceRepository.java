package out.repo;

import model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import out.entity.user.UserAccountEntity;

import java.util.Optional;

@Repository
public interface UserAccountPersistenceRepository extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> findByEmail(String email);

    boolean existByEmail(String email);


}
