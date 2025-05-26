package out.repo.persistence;

import lombok.AllArgsConstructor;
import model.shared.Email;
import model.user.UserAccount;
import org.springframework.stereotype.Component;
import out.UserAccountRepository;
import out.entity.user.UserAccountEntity;
import out.mapper.UserAccountEntityMapper;
import out.repo.UserAccountPersistenceRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserAccountPersistence implements UserAccountRepository {

    private UserAccountPersistenceRepository userAccountPersistenceRepository;
    private UserAccountEntityMapper userAccountEntityMapper;

    @Override
    public Optional<UserAccount> findByEmail(Email email) {
        return userAccountPersistenceRepository.findByEmail(email.value())
                .map(userAccountEntityMapper::toUserAccount);
    }

    @Override
    public Optional<UserAccount> findById(long userAccountId) {
        return userAccountPersistenceRepository.findById(userAccountId)
                .map(userAccountEntityMapper::toUserAccount);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        UserAccountEntity userAccountEntity = userAccountEntityMapper.toUserAccountEntity(userAccount);
        UserAccountEntity savedUserAccountEntity = userAccountPersistenceRepository.save(userAccountEntity);

        return userAccountEntityMapper.toUserAccount(savedUserAccountEntity);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userAccountPersistenceRepository.existByEmail(email.value());
    }
}
