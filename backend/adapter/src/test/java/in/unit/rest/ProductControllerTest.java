package in.unit.rest;

import in.dto.restaurant.ProductDto;
import in.mapper.ProductMapperImpl;
import in.mapper.UserMapperImpl;
import in.rest.ProductController;
import model.restaurant.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import out.RestaurantRepository;
import service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static service.Fixtures.*;

@ContextConfiguration(classes = {ProductMapperImpl.class, UserMapperImpl.class})
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock private ProductService productService;
    @Autowired private ProductMapperImpl productMapper = new ProductMapperImpl();
    private ProductController controller;

    @BeforeEach
    public void setup() {
        controller = new ProductController(productService, productMapper);
    }

    @Test
    public void testFindProductById() {
        ProductDto placinteDto = productMapper.toProductDto(placinte);
        when(productService.findById(placinte.id())).thenReturn(placinte);
        ResponseEntity<ProductDto> response = controller.findById(placinte.id());

        assertProductCreation(placinteDto, response, HttpStatus.OK);
    }

    @Test
    public void testCreateProduct() {
        ProductDto placinteDto = productMapper.toProductDto(placinte);
        when(productService.createProduct(eq(laPlacinte.id()), any(Product.class))).thenReturn(placinte);
        ResponseEntity<ProductDto> response = controller.createProduct(laPlacinte.id(), placinteDto);

        assertProductCreation(placinteDto, response, HttpStatus.CREATED);
    }

    @Test
    public void testUpdateProduct() {
        Product expectedUpdatedProduct = placinte.toBuilder()
                .description("placinte ca la bunicta")
                .build();
        when(productService.updateProduct(eq(placinte.id()), any(Product.class))).thenReturn(expectedUpdatedProduct);
        ProductDto expectedUpdatedProductDto = productMapper.toProductDto(expectedUpdatedProduct);
        ResponseEntity<ProductDto> response = controller.updateProduct(placinte.id(), expectedUpdatedProductDto);

        assertProductCreation(expectedUpdatedProductDto, response, HttpStatus.OK);
    }

    @Test
    public void testDeleteProductById() {
        ResponseEntity<Void> response = controller.deleteProductById(placinte.id());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testReduceProductQuantity() {
        final int reduceBy = 1;
        final Product expectedReducedPlacinte = reservedPlacinte.reduce(reduceBy);

        when(productService.reduceQuantity(reservedPlacinte.id(), reduceBy)).thenReturn(expectedReducedPlacinte);
        ProductDto expectedReducedPlacinteDto = productMapper.toProductDto(expectedReducedPlacinte);
        ResponseEntity<ProductDto> response = controller.reduceQuantity(reservedPlacinte.id(), reduceBy);

        assertProductCreation(expectedReducedPlacinteDto, response, HttpStatus.OK);
    }

    @Test
    public void testReserveProductQuantity() {
        final int reserveBy = 3;
        when(productService.reserveQuantity(placinte.id(), reserveBy)).thenReturn(reservedPlacinte);
        ProductDto expectedReservedPlacinteDto = productMapper.toProductDto(reservedPlacinte);
        ResponseEntity<ProductDto> response = controller.reserveQuantity(placinte.id(), reserveBy);

        assertProductCreation(expectedReservedPlacinteDto, response, HttpStatus.OK);
    }

    private void assertProductCreation(
            ProductDto expectedProductDto,
            ResponseEntity<ProductDto> responseProduct,
            HttpStatus expectedHttpStatus) {

        final ProductDto responseProductBody = responseProduct.getBody();

        assertEquals(expectedHttpStatus, responseProduct.getStatusCode());
        assertNotNull(responseProductBody);
        assertEquals(expectedProductDto, responseProductBody);
        assertEquals(expectedProductDto.name(), responseProductBody.name());
        assertEquals(expectedProductDto.restaurantDto().name(), responseProductBody.restaurantDto().name());
        assertEquals(expectedProductDto.restaurantDto().locationDto().addressDto().value(),
                responseProductBody.restaurantDto().locationDto().addressDto().value());
        assertEquals(expectedProductDto.pickUpTime().getHour(), responseProductBody.pickUpTime().getHour());
        assertEquals(expectedProductDto.description(), responseProductBody.description());
        assertEquals(expectedProductDto.totalQuantity(), responseProductBody.totalQuantity());
        assertEquals(expectedProductDto.reservedQuantity(), responseProductBody.reservedQuantity());
        assertEquals(expectedProductDto.price(), responseProductBody.price());
    }
}
