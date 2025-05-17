package service;

import in.RestaurantUseCase;
import lombok.AllArgsConstructor;
import model.payment.BankingDetails;
import model.restaurant.Location;
import model.restaurant.Product;
import model.restaurant.Restaurant;
import org.springframework.stereotype.Service;
import out.RestaurantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService implements RestaurantUseCase {

    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findRestaurantById(long restaurantId) {
        return null;
    }

    @Override
    public Restaurant createNewRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant updateLocation(long restaurantId, Location updatedLocation) {
        return null;
    }

    @Override
    public Restaurant updateBankingDetails(long restaurantId, BankingDetails updatedBankingDetails) {
        return null;
    }

    @Override
    public Restaurant updateProductList(long restaurantId, List<Product> products) {
        return null;
    }
}
