package service;

import model.restaurant.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import out.ProductRepository;
import out.RestaurantRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.Fixtures.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock private RestaurantRepository restaurantRepository;
    @Mock private ProductRepository productRepository;
    @InjectMocks private ProductService productService;

    @Test
    public void testFindProductById() {
        when(productRepository.findById(placinte.id())).thenReturn(Optional.of(placinte));
        Product foundPlacinte = productService.findById(placinte.id());

        verify(productRepository, times(1)).findById(placinte.id());

        assertEquals(placinte, foundPlacinte);
    }

    @Test
    public void testFindAvailableProducts() {
        final int page = 1;
        final int size = 10;
        List<Product> expectedProducts = List.of(placinte, jumeri);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pageProducts = new PageImpl<>(expectedProducts, pageable, expectedProducts.size());

        when(productRepository.findAvailableProducts(PageRequest.of(page, size))).thenReturn(pageProducts);

        Page<Product> products = productService.findAvailableProducts(page, size);

        verify(productRepository, times(1)).findAvailableProducts(PageRequest.of(page, size));

        assertEquals(expectedProducts.size(), products.getNumberOfElements());
        assertEquals(expectedProducts, products.stream().toList());
    }

    @Test
    public void testFindAllProductsByRestaurant() {
        List<Product> expectedProducts = List.of(placinte, jumeri);
        when(productRepository.findAllByRestaurantId(laPlacinte.id())).thenReturn(expectedProducts);

        List<Product> restaurantProducts = productService.findAllByRestaurant(laPlacinte.id());

        verify(productRepository, times(1)).findAllByRestaurantId(laPlacinte.id());

        assertEquals(expectedProducts.size(), restaurantProducts.size());
        assertEquals(expectedProducts, restaurantProducts);

    }

    @Test
    public void testCreateProduct() {
        when(restaurantRepository.findById(laPlacinte.id())).thenReturn(Optional.of(laPlacinte));
        Product expectedProduct = placinte.toBuilder()
                .restaurant(laPlacinte)
                .build();
        when(productRepository.save(expectedProduct)).thenReturn(expectedProduct);

        Product createdProduct = productService.createProduct(laPlacinte.id(), placinte);

        verify(restaurantRepository, times(1)).findById(laPlacinte.id());
        verify(productRepository, times(1)).save(expectedProduct);

        assertEquals(expectedProduct, createdProduct);
    }

    @Test
    public void testUpdateProduct() {
        when(productRepository.findById(placinte.id())).thenReturn(Optional.of(placinte));
        Product expectedUpdatedProduct = placinte.toBuilder()
                .description("placinte ca la bunicta")
                .build();
        when(productRepository.save(expectedUpdatedProduct)).thenReturn(expectedUpdatedProduct);

        Product updatedProduct = productService.updateProduct(placinte.id(), expectedUpdatedProduct);
        assertEquals(expectedUpdatedProduct, updatedProduct);
    }

    @Test
    public void testDeleteProductById() {
        productService.deleteProductById(placinte.id());

        verify(productRepository, times(1)).deleteById(placinte.id());
    }

    @Test
    void shouldThrowIfQuantityIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> productService.reduceQuantity(placinte.id(), 0));
        assertThrows(IllegalArgumentException.class, () -> productService.reduceQuantity(placinte.id(), -1));
    }

    @Test
    public void testReduceProductQuantity() {
        final int reduceBy = 1;
        final Product expectedReduced = reservedPlacinte.reduce(reduceBy);

        when(productRepository.findById(placinte.id())).thenReturn(Optional.of(reservedPlacinte));
        when(productRepository.save(expectedReduced)).thenReturn(expectedReduced);

        Product result = productService.reduceQuantity(reservedPlacinte.id(), reduceBy);

        verify(productRepository).findById(placinte.id());
        verify(productRepository).save(expectedReduced);

        assertEquals(expectedReduced, result);
    }

    @Test
    public void testReserveProductQuantity() {
        when(productRepository.findById(placinte.id())).thenReturn(Optional.of(placinte));
        when(productRepository.save(reservedPlacinte)).thenReturn(reservedPlacinte);

        Product reservedProduct = productService.reserveQuantity(placinte.id(), 3);

        verify(productRepository).findById(placinte.id());
        verify(productRepository).save(reservedPlacinte);

        assertEquals(reservedPlacinte, reservedProduct);
    }
}
