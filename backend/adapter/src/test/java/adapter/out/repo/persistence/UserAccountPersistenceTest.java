package adapter.out.repo.persistence;

import model.user.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.entity.user.UserAccountEntity;
import out.mapper.UserAccountEntityMapper;
import out.repo.UserAccountPersistenceRepository;
import out.repo.persistence.UserAccountPersistence;

import java.util.Optional;

import static adapter.Fixtures.clientEmail;
import static adapter.Fixtures.vaneaUserAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountPersistenceTest {

    @Mock private UserAccountPersistenceRepository userAccountPersistenceRepository;

    @Mock private UserAccountEntityMapper userAccountEntityMapper;

    @InjectMocks private UserAccountPersistence userAccountPersistence;

    @Test
    void findByEmail_returnsMappedUserAccount_whenFound() {
        UserAccountEntity entity = mock(UserAccountEntity.class);

        when(userAccountPersistenceRepository.findByEmail(clientEmail.value())).thenReturn(Optional.of(entity));
        when(userAccountEntityMapper.toUserAccount(entity)).thenReturn(vaneaUserAccount);

        Optional<UserAccount> result = userAccountPersistence.findByEmail(clientEmail);

        assertTrue(result.isPresent());
        assertEquals(vaneaUserAccount, result.get());
        verify(userAccountPersistenceRepository).findByEmail(clientEmail.value());
        verify(userAccountEntityMapper).toUserAccount(entity);
    }

    @Test
    void findByEmail_returnsEmpty_whenNotFound() {
        when(userAccountPersistenceRepository.findByEmail(clientEmail.value())).thenReturn(Optional.empty());

        Optional<UserAccount> result = userAccountPersistence.findByEmail(clientEmail);

        assertTrue(result.isEmpty());
        verify(userAccountPersistenceRepository).findByEmail(clientEmail.value());
        verifyNoInteractions(userAccountEntityMapper);
    }

    @Test
    void findById_returnsMappedUserAccount_whenFound() {
        long id = vaneaUserAccount.id();
        UserAccountEntity entity = mock(UserAccountEntity.class);

        when(userAccountPersistenceRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userAccountEntityMapper.toUserAccount(entity)).thenReturn(vaneaUserAccount);

        Optional<UserAccount> result = userAccountPersistence.findById(id);

        assertTrue(result.isPresent());
        assertEquals(vaneaUserAccount, result.get());
        verify(userAccountPersistenceRepository).findById(id);
        verify(userAccountEntityMapper).toUserAccount(entity);
    }

    @Test
    void save_mapsAndPersistsUserAccount() {
        UserAccountEntity toSave = mock(UserAccountEntity.class);
        UserAccountEntity saved = mock(UserAccountEntity.class);

        when(userAccountEntityMapper.toUserAccountEntity(vaneaUserAccount)).thenReturn(toSave);
        when(userAccountPersistenceRepository.save(toSave)).thenReturn(saved);
        when(userAccountEntityMapper.toUserAccount(saved)).thenReturn(vaneaUserAccount);

        UserAccount result = userAccountPersistence.save(vaneaUserAccount);

        assertEquals(vaneaUserAccount, result);
        verify(userAccountEntityMapper).toUserAccountEntity(vaneaUserAccount);
        verify(userAccountPersistenceRepository).save(toSave);
        verify(userAccountEntityMapper).toUserAccount(saved);
    }

    @Test
    void existsByEmail_delegatesCorrectly() {
        when(userAccountPersistenceRepository.existByEmail(clientEmail.value())).thenReturn(true);

        boolean exists = userAccountPersistence.existsByEmail(clientEmail);

        assertTrue(exists);
        verify(userAccountPersistenceRepository).existByEmail(clientEmail.value());
    }
}
