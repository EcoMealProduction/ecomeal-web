package out.mapper;

import model.restaurant.Product;
import model.shared.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import out.entity.restaurant.ProductEntity;

import java.math.BigDecimal;
import java.util.Currency;

@Mapper(componentModel = "spring", uses = {
        RestaurantEntityMapper.class,
        UserAccountEntityMapper.class})
public interface ProductEntityMapper {

    @Mapping(target = "price", expression = "java(toDomain(entity.getPriceCurrency(), entity.getPriceAmount()))")
    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductEntity entity);

    @Mapping(target = "priceCurrency", source = "price", qualifiedByName = "extractCurrency")
    @Mapping(target = "priceAmount", source = "price", qualifiedByName = "extractAmount")
    ProductEntity toProductEntity(Product product);

    default Money toDomain(Currency currency, BigDecimal amount) {
        return new Money(currency, amount);
    }

    @Named("extractCurrency")
    default Currency extractCurrency(Money money) {
        return money.currency();
    }

    @Named("extractAmount")
    default BigDecimal extractAmount(Money money) {
        return money.amount();
    }

}
