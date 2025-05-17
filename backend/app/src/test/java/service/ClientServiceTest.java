package service;

import model.client.Client;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.shared.Address;
import model.shared.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import out.ClientRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static service.Fixtures.vanea;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock private ClientRepository clientRepository;
    @InjectMocks private ClientService clientService;

    @Test
    public void testFindingNewClient() {
        when(clientRepository.findById(vanea.id())).thenReturn(Optional.of(vanea));
        Client foundVanea = clientService.findClientById(vanea.id());

        verify(clientRepository, times(1)).findById(vanea.id());

        assertEquals(vanea, foundVanea);
    }

    @Test
    public void testCreateNewClient() {
        when(clientRepository.save(vanea)).thenReturn(vanea);
        Client savedVanea = clientService.createNewClient(vanea);

        verify(clientRepository, times(1)).save(vanea);

        assertEquals(vanea, savedVanea);
    }

    @Test
    public void testUpdateClientLocation() {
        final Location newLocation = new Location(
                new Address("The Great National Assembly Square, Chișinău, Republik Moldau"),
                BigDecimal.valueOf(47.02515442373227),
                BigDecimal.valueOf(28.83257176316544));

        Client vaneaNewLocation = vanea.toBuilder()
                .location(newLocation)
                .build();

        when(clientRepository.findById(vanea.id())).thenReturn(Optional.of(vanea));
        assertTrue(clientRepository.findById(vanea.id()).isPresent());

        when(clientRepository.save(any(Client.class))).thenReturn(vaneaNewLocation);
        final Client vaneaWithUpdateLocation = clientService.updateLocation(vanea.id(), newLocation);

        assertEquals(vaneaNewLocation.location(), vaneaWithUpdateLocation.location());
    }

    @Test
    public void testUpdateBankingDetails() {
        final BankingDetails newBankingDetails = new BankingDetails(
                2L,
                "Victoria Bank",
                new Country("Moldova", "MD"),
                "MD234",
                "Chisinau",
                "strada mamei tale");

        Client vaneaNewBankingDetails = vanea.toBuilder()
                .bankingDetails(newBankingDetails)
                .build();

        when(clientRepository.findById(vanea.id())).thenReturn(Optional.of(vanea));
        assertTrue(clientRepository.findById(vanea.id()).isPresent());

        when(clientRepository.save(any(Client.class))).thenReturn(vaneaNewBankingDetails);

        verify(clientRepository, times(1)).findById(vanea.id());

        final Client vaneaWithUpdateBankingDetails = clientService.updateBankingDetails(vanea.id(), newBankingDetails);

        assertEquals(vaneaNewBankingDetails.bankingDetails(), vaneaWithUpdateBankingDetails.bankingDetails());
    }

    @Test
    public void testUpdatePickUpRadius() {
        Client vaneaWithNewPickUpRadius = vanea.toBuilder()
                .pickUpRadiusKm(30)
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.of(vanea));
        assertTrue(clientRepository.findById(1L).isPresent());

        when(clientRepository.save(any(Client.class))).thenReturn(vaneaWithNewPickUpRadius);

        Client vaneaWithUpdatePickUpRadius = clientService.updatePickUpRadius(1L, 30);

        assertEquals(vaneaWithNewPickUpRadius, vaneaWithUpdatePickUpRadius);
    }

    @Test
    public void testFindNearbyRestaurants() {

    }

}
