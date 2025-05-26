package adapter.out.mapper;

import model.user.Role;
import model.user.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.user.UserAccountEntity;
import out.mapper.UserAccountEntityMapper;
import out.mapper.UserAccountEntityMapperImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static adapter.Fixtures.*;

@ContextConfiguration(classes = {UserAccountEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class UserAccountEntityMapperTest {

    @Autowired private UserAccountEntityMapper userAccountEntityMapper;

    @Test
    public void testMapToUserAccountEntity() {
        UserAccountEntity userAccountEntity = userAccountEntityMapper.toUserAccountEntity(vaneaUserAccount);

        assertEquals(clientEmail.value(), userAccountEntity.getEmail());
        assertEquals(clientPhoneNumber.value(), userAccountEntity.getPhoneNumber());
        assertEquals(Role.CLIENT, userAccountEntity.getRole());
        assertEquals(clientPassword.value(), userAccountEntity.getPassword());
    }

    @Test
    public void testMapToUserAccount() {
        UserAccountEntity userAccountEntity = userAccountEntityMapper.toUserAccountEntity(vaneaUserAccount);
        UserAccount userAccount = userAccountEntityMapper.toUserAccount(userAccountEntity);

        assertEquals(clientEmail.value(), userAccount.email().value());
        assertEquals(clientPhoneNumber.value(), userAccount.phoneNumber().value());
        assertEquals(Role.CLIENT, userAccount.role());
        assertEquals(clientPassword.value(), userAccount.password().value());
    }
}
