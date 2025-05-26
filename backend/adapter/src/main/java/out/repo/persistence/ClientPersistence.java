package out.repo.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import model.client.Client;
import out.ClientRepository;
import out.entity.client.ClientEntity;
import out.mapper.ClientEntityMapper;
import out.repo.ClientPersistenceRepository;

@Component
@AllArgsConstructor
public class ClientPersistence implements ClientRepository {

    private ClientPersistenceRepository clientPersistenceRepository;
    private ClientEntityMapper clientEntityMapper;

    @Override
    public Optional<Client> findById(long clientId) {
        return clientPersistenceRepository.findById(clientId)
                .map(clientEntityMapper::toClient);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientEntityMapper.toClientEntity(client);
        ClientEntity savedClientEntity = clientPersistenceRepository.save(clientEntity);

        return clientEntityMapper.toClient(savedClientEntity);
    }

    @Override
    public void deleteById(long clientId) {
        if (!clientPersistenceRepository.existsById(clientId))
            throw new EntityNotFoundException("Client not found");

        clientPersistenceRepository.deleteById(clientId);
    }
}
