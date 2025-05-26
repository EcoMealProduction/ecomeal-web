package out.entity.restaurant;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.util.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "reserved_quantity")
    private int reservedQuantity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column(name = "price_amount")
    private BigDecimal priceAmount;

    @Column(name = "price_currency")
    private Currency priceCurrency;

    @Column(name = "pick_up_time")
    private OffsetTime pickUpTime;

    public ProductEntity(String name, String description, int totalQuantity, int reservedQuantity,
                         RestaurantEntity restaurant, BigDecimal priceAmount, Currency priceCurrency,
                         OffsetTime pickUpTime) {
        this.name = name;
        this.description = description;
        this.totalQuantity = totalQuantity;
        this.reservedQuantity = reservedQuantity;
        this.restaurant = restaurant;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.pickUpTime = pickUpTime;
    }

    public ProductEntity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public Currency getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(Currency priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public OffsetTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(OffsetTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
