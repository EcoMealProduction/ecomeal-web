package adapter.out.repo.persistence;

import jakarta.persistence.EntityNotFoundException;
import model.client.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.entity.client.ClientEntity;
import out.mapper.ClientEntityMapper;
import out.repo.ClientPersistenceRepository;
import out.repo.persistence.ClientPersistence;

import java.util.Optional;

import static adapter.Fixtures.vanea;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientPersistenceTest {

    @Mock private ClientPersistenceRepository clientPersistenceRepository;

    @Mock private ClientEntityMapper clientEntityMapper;

    @InjectMocks private ClientPersistence clientPersistence;

    @Test
    public void testFindClientById() {
        long clientId = vanea.id();
        ClientEntity mockEntity = mock(ClientEntity.class);

        when(clientPersistenceRepository.findById(clientId)).thenReturn(Optional.of(mockEntity));
        when(clientEntityMapper.toClient(mockEntity)).thenReturn(vanea);

        Optional<Client> result = clientPersistence.findById(clientId);

        assertTrue(result.isPresent());
        assertEquals(vanea, result.get());
    }

    @Test
    public void testClientByIdNotFound() {
        long clientId = vanea.id();

        when(clientPersistenceRepository.findById(clientId)).thenReturn(Optional.empty());

        Optional<Client> result = clientPersistence.findById(clientId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSaveClient() {
        ClientEntity entityToSave = mock(ClientEntity.class);
        ClientEntity savedEntity = mock(ClientEntity.class);

        when(clientEntityMapper.toClientEntity(vanea)).thenReturn(entityToSave);
        when(clientPersistenceRepository.save(entityToSave)).thenReturn(savedEntity);
        when(clientEntityMapper.toClient(savedEntity)).thenReturn(vanea);

        Client result = clientPersistence.save(vanea);

        assertEquals(vanea, result);
    }

    @Test
    public void testDeleteClient() {
        long id = vanea.id();

        when(clientPersistenceRepository.existsById(id)).thenReturn(true);
        clientPersistence.deleteById(id);
    }

    @Test
    public void testClientNotFound() {
        long id = 999L;

        when(clientPersistenceRepository.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> clientPersistence.deleteById(id));
    }
}
