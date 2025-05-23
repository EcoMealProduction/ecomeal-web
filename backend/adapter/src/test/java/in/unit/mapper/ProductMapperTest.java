package in.unit.mapper;

import in.dto.restaurant.ProductDto;
import in.mapper.ProductMapper;
import in.mapper.ProductMapperImpl;
import in.mapper.UserMapperImpl;
import model.restaurant.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.Fixtures.placinte;

@ContextConfiguration(classes = {ProductMapperImpl.class, UserMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class ProductMapperTest {

    @Autowired private ProductMapper productMapper;

    @Test
    public void testMapToProductDto() {
        ProductDto productDto = productMapper.toProductDto(placinte);

        assertEquals(productDto.name(), placinte.name());
        assertEquals(productDto.description(), placinte.description());
        assertEquals(productDto.reservedQuantity(), placinte.reservedQuantity());
        assertEquals(productDto.price().amount(), placinte.price().amount());
        assertEquals(productDto.pickUpTime(), placinte.pickUpTime());
        assertEquals(productDto.restaurantDto().name(), placinte.restaurant().name());
        assertEquals(productDto.restaurantDto().locationDto().addressDto().value(),
                placinte.restaurant().location().address().value());
    }

    @Test
    public void testMapToProduct() {
        ProductDto productDto = productMapper.toProductDto(placinte);
        Product product = productMapper.toProduct(productDto);

        assertEquals(product.name(), placinte.name());
        assertEquals(product.description(), placinte.description());
        assertEquals(product.reservedQuantity(), placinte.reservedQuantity());
        assertEquals(product.price().amount(), placinte.price().amount());
        assertEquals(product.pickUpTime(), placinte.pickUpTime());
        assertEquals(product.restaurant().name(), placinte.restaurant().name());
        assertEquals(product.restaurant().location().address().value(),
                placinte.restaurant().location().address().value());
    }
}
