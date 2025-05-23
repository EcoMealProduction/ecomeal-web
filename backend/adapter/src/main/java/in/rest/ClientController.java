package in.rest;

import in.dto.client.ClientDto;
import in.dto.restaurant.RestaurantDto;
import in.mapper.UserMapper;
import model.client.Client;
import model.restaurant.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.List;

/**
 * REST controller for client-related operations.
 * Handles client profile lookup, pickup radius updates, and discovery of nearby restaurants.
 */
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private ClientService clientService;
    private UserMapper userMapper;

    public ClientController(ClientService clientService, UserMapper userMapper) {
        this.clientService = clientService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param clientId the ID of the client
     * @return the client as {@link ClientDto}
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable long clientId) {
        Client client = clientService.findClientById(clientId);
        ClientDto clientDto = userMapper.toClientDto(client);

        return ResponseEntity.ok(clientDto);
    }

    /**
     * Updates the client's preferred pickup radius.
     *
     * @param clientId the ID of the client
     * @param updatedPickUpRadius new pickup radius in kilometers
     * @return updated {@link ClientDto}
     */
    @PutMapping("/{clientId}/pickUpRadius")
    public ResponseEntity<ClientDto> updatePickUpRadius(
            @PathVariable long clientId,
            @RequestParam double updatedPickUpRadius) {
        Client client = clientService.updatePickUpRadius(clientId, updatedPickUpRadius);
        ClientDto clientDto = userMapper.toClientDto(client);

        return ResponseEntity.ok(clientDto);
    }

    /**
     * Returns a list of restaurants within the client's pickup radius.
     *
     * ⚠️ WARNING: This implementation incorrectly uses a request parameter to pass a list of complex objects.
     * Instead, this method should retrieve all restaurants from a service or repository.
     *
     * @param clientId the ID of the client
     * @return list of nearby {@link RestaurantDto}s
     */
    @GetMapping("/{clientId}/restaurants")
    public ResponseEntity<List<RestaurantDto>> findNearbyRestaurants(
            @PathVariable long clientId,
            @RequestParam List<Restaurant> allRestaurants) { // ⚠️ This won't work as expected

        List<Restaurant> restaurants = clientService.findNearbyRestaurants(clientId, allRestaurants);
        List<RestaurantDto> restaurantDtos = restaurants.stream()
                .map(userMapper::toRestaurantDto)
                .toList();

        return ResponseEntity.ok(restaurantDtos);
    }
}
