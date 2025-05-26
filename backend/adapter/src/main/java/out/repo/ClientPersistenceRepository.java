package out.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import out.entity.client.ClientEntity;

@Repository
public interface ClientPersistenceRepository extends JpaRepository<ClientEntity, Long> { }
