package in.unit.mapper;

import in.dto.client.ClientDto;
import in.dto.restaurant.RestaurantDto;
import in.dto.user.UserAccountDto;
import in.mapper.UserMapper;
import in.mapper.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.Fixtures.*;

@ContextConfiguration(classes = UserMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class UserMapperTest {

    @Autowired private UserMapper userMapper;

    @Test
    public void testMapToUserAccountDto() {
        UserAccountDto userAccountDto = userMapper.toUserAccountDto(vaneaUserAccount);

        assertEquals(userAccountDto.emailDto().value(), clientEmail.value());
        assertEquals(userAccountDto.phoneNumberDto().value(), clientPhoneNumber.value());
        assertEquals(userAccountDto.role(), vaneaUserAccount.role());
    }

    @Test
    public void testMapToClientDto() {
        ClientDto clientDto = userMapper.toClientDto(vanea);

        assertEquals(clientDto.firstName(), vanea.firstName());
        assertEquals(clientDto.lastName(), vanea.lastName());
        assertEquals(clientDto.country().name(), moldova.name());
        assertEquals(clientDto.bankingDetailsDto().name(), bankingDetails.name());
        assertEquals(clientDto.userAccountDto().emailDto().value(), clientEmail.value());
    }

    @Test
    public void testMapToRestaurantDto() {
        RestaurantDto restaurantDto = userMapper.toRestaurantDto(laPlacinte);

        assertEquals(restaurantDto.name(), laPlacinte.name());
        assertEquals(restaurantDto.description(), laPlacinte.description());
        assertEquals(restaurantDto.locationDto().addressDto().value(), laPlacinteAddress.value());
        assertEquals(restaurantDto.userAccountDto().emailDto().value(), restaurantEmail.value());
        assertEquals(restaurantDto.bankingDetailsDto().name(), bankingDetails.name());
        assertEquals(restaurantDto.bankingDetailsDto().country(), bankingDetails.country());
    }
}
