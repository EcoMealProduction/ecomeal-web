package adapter.in.unit.rest;

import in.dto.client.ClientDto;
import in.mapper.UserMapperImpl;
import in.rest.ClientController;
import model.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import service.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static adapter.Fixtures.vanea;

@ContextConfiguration(classes = {UserMapperImpl.class})
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock private ClientService clientService;
    @Autowired private UserMapperImpl userMapper = new UserMapperImpl();
    private ClientController controller;

    @BeforeEach
    public void setup() {
        controller = new ClientController(clientService, userMapper);
    }

    @Test
    public void testFindClientById() {
        ClientDto vaneaDto = userMapper.toClientDto(vanea);
        when(clientService.findClientById(vanea.id())).thenReturn(vanea);
        ResponseEntity<ClientDto> response = controller.findClientById(vanea.id());

        final ClientDto responseClientBody = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseClientBody);
        assertEquals(vaneaDto, responseClientBody);
        assertEquals(vaneaDto.firstName(), responseClientBody.firstName());
        assertEquals(vaneaDto.lastName(), responseClientBody.lastName());
        assertEquals(vaneaDto.userAccountDto(), responseClientBody.userAccountDto());
        assertEquals(vaneaDto.country(), responseClientBody.country());
        assertEquals(vaneaDto.locationDto(), responseClientBody.locationDto());
        assertEquals(vaneaDto.bankingDetailsDto(), responseClientBody.bankingDetailsDto());
        assertEquals(vaneaDto.pickUpRadiusKm(), responseClientBody.pickUpRadiusKm());
    }

    @Test
    public void testUpdatePickupRadius() {
        Client expectedVanea = vanea.toBuilder()
                .pickUpRadiusKm(22)
                .build();

        when(clientService.updatePickUpRadius(vanea.id(), 22)).thenReturn(expectedVanea);
        ResponseEntity<ClientDto> response = controller.updatePickUpRadius(vanea.id(), 22);

        final ClientDto responseClientBody = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseClientBody);
        assertEquals(22, responseClientBody.pickUpRadiusKm());
    }

    @Test
    public void testFindNearbyRestaurants() {

    }
}
