package in.mapper;

import in.dto.restaurant.ProductDto;
import in.dto.shared.MoneyDto;
import model.restaurant.Product;
import model.shared.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper interface for converting between {@link Product} and {@link ProductDto}.
 * Also handles nested mapping via {@link UserMapper} and custom mapping of {@link Money} values.
 */
@Mapper(uses = {UserMapper.class})
public interface ProductMapper {

    /**
     * Maps a {@link Product} domain object to a {@link ProductDto}.
     *
     * @param product the product domain model
     * @return the mapped {@link ProductDto}
     */
    @Mapping(target = "restaurantDto", source = "restaurant")
    ProductDto toProductDto(Product product);

    /**
     * Maps a {@link ProductDto} back to a {@link Product} domain object.
     * The product ID is ignored during the mapping.
     *
     * @param productDto the DTO to convert
     * @return the mapped {@link Product}
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", source = "restaurantDto")
    Product toProduct(ProductDto productDto);

    /**
     * Manually maps a {@link Money} domain object to a {@link MoneyDto}.
     *
     * @param value the monetary value
     * @return the equivalent {@link MoneyDto}
     */
    default MoneyDto map(Money value) {
        return new MoneyDto(value.currency(), value.amount());
    }

}
