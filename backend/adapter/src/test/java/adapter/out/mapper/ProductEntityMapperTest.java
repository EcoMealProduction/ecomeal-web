package adapter.out.mapper;

import model.restaurant.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import out.entity.restaurant.ProductEntity;
import out.mapper.*;

import static adapter.Fixtures.placinte;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {
        ProductEntityMapperImpl.class,
        RestaurantEntityMapperImpl.class,
        BankingDetailsEntityMapperImpl.class,
        UserAccountEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class ProductEntityMapperTest {

    @Autowired private ProductEntityMapper productEntityMapper;

    @Test
    public void testMapToProductEntity() {
        ProductEntity productEntity = productEntityMapper.toProductEntity(placinte);

        assertEquals(placinte.name(), productEntity.getName());
        assertEquals(placinte.restaurant().name(), productEntity.getRestaurant().getName());
        assertEquals(placinte.restaurant().location().address().value(),
                productEntity.getRestaurant().getAddress());
        assertEquals(placinte.price().amount(), productEntity.getPriceAmount());
        assertEquals(placinte.totalQuantity(), productEntity.getTotalQuantity());
        assertEquals(placinte.pickUpTime(), productEntity.getPickUpTime());
        assertEquals(placinte.reservedQuantity(), productEntity.getReservedQuantity());
    }

    @Test
    public void testMapToProduct() {
        ProductEntity productEntity = productEntityMapper.toProductEntity(placinte);
        Product product = productEntityMapper.toProduct(productEntity);

        assertEquals(placinte.name(), product.name());
        assertEquals(placinte.restaurant().name(), product.restaurant().name());
        assertEquals(placinte.restaurant().location().address(),
                product.restaurant().location().address());
        assertEquals(placinte.price().amount(), product.price().amount());
        assertEquals(placinte.totalQuantity(), product.totalQuantity());
        assertEquals(placinte.pickUpTime(), product.pickUpTime());
        assertEquals(placinte.reservedQuantity(), product.reservedQuantity());
    }
}
