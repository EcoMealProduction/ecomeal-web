package in.unit.rest;

import in.dto.restaurant.ProductDto;
import in.mapper.ProductMapperImpl;
import in.mapper.UserMapperImpl;
import in.rest.MarketplaceController;
import model.restaurant.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import service.ProductService;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static service.Fixtures.*;

@ContextConfiguration(classes = {ProductMapperImpl.class, UserMapperImpl.class})
@ExtendWith(MockitoExtension.class)
public class MarketPlaceControllerTest {


    @Mock private ProductService productService;
    @Autowired private ProductMapperImpl productMapper = new ProductMapperImpl();
    private MarketplaceController controller;

    @BeforeEach
    public void setup() {
        controller = new MarketplaceController(productService, productMapper);
    }

    @Test
    public void testReturnListOfProducts() {
        final int page = 1;
        final int size = 10;

        ProductDto placinteDto = productMapper.toProductDto(placinte);
        ProductDto jumeriDto = productMapper.toProductDto(jumeri);
        List<Product> expectedProducts = List.of(placinte, jumeri);
        List<ProductDto> expectedProductDtos = List.of(placinteDto, jumeriDto);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> expectedPage = new PageImpl<>(expectedProducts, pageable, expectedProducts.size());

        when(productService.findAvailableProducts(page, size)).thenReturn(expectedPage);

        ResponseEntity<List<ProductDto>> response = controller.findAvailableProducts(page, size);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        ProductDto placinteResponse = response.getBody().getFirst();
        ProductDto jumeriResponse = response.getBody().getLast();

        assertEquals(expectedProductDtos, response.getBody());
        assertEquals(placinteDto, placinteResponse);
        assertEquals(jumeriDto, jumeriResponse);
        assertEquals("La Placinte", placinteResponse.restaurantDto().name());
        assertEquals("placinte", placinteResponse.name());
        assertEquals(OffsetDateTime.now().plusHours(3).getHour(), placinteResponse.pickUpTime().getHour());
        assertEquals("bl. Dacia 111", placinteDto.restaurantDto().locationDto().addressDto().value());
        assertEquals(2, response.getBody().size());
    }

}
