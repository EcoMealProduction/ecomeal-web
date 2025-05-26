package adapter.out.mapper;

import model.client.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.client.ClientEntity;
import out.mapper.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static adapter.Fixtures.vanea;

@ContextConfiguration(classes = {
        ClientEntityMapperImpl.class,
        UserAccountEntityMapperImpl.class,
        BankingDetailsEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class ClientEntityMapperTest {

    @Autowired private ClientEntityMapper clientEntityMapper;

    @Test
    public void testMapToClientEntity() {
        ClientEntity clientEntity = clientEntityMapper.toClientEntity(vanea);

        assertEquals(vanea.firstName(), clientEntity.getFirstName());
        assertEquals(vanea.lastName(), clientEntity.getLastName());
        assertEquals(vanea.bankingDetails().name(), clientEntity.getBankingDetails().getName());
        assertEquals(vanea.userAccount().email().value(), clientEntity.getUserAccount().getEmail());
        assertEquals(vanea.userAccount().password().value(), clientEntity.getUserAccount().getPassword());
        assertEquals(vanea.userAccount().phoneNumber().value(), clientEntity.getUserAccount().getPhoneNumber());
        assertEquals(vanea.userAccount().role(), clientEntity.getUserAccount().getRole());
    }

    @Test
    public void testMapToClient() {
        ClientEntity clientEntity = clientEntityMapper.toClientEntity(vanea);
        Client client = clientEntityMapper.toClient(clientEntity);

        assertEquals(vanea.firstName(), client.firstName());
        assertEquals(vanea.lastName(), client.lastName());
        assertEquals(vanea.bankingDetails().name(), client.bankingDetails().name());
        assertEquals(vanea.userAccount().email().value(), client.userAccount().email().value());
        assertEquals(vanea.userAccount().password().value(), client.userAccount().password().value());
        assertEquals(vanea.userAccount().phoneNumber().value(), client.userAccount().phoneNumber().value());
        assertEquals(vanea.userAccount().role(), client.userAccount().role());
    }
}
