package restaurant;

import model.restaurant.Location;
import model.shared.Address;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationTest {

    private final Address aachenerNobisAddress = new Address("Münsterpl. 3, 52062 Aachen");
    private final BigDecimal aachenerNobisLatitude = BigDecimal.valueOf(50.774640969053024);
    private final BigDecimal aachenerNobisLongitude = BigDecimal.valueOf(6.08490228561747);

    private final Address aachenerDomAddress = new Address("Domhof 1, 52062 Aachen");
    private final BigDecimal aachenerDomLatitude = BigDecimal.valueOf(50.77473538509855);
    private final BigDecimal aachenerDomLongitude = BigDecimal.valueOf(6.083920847968691);

    private final Address albertHeijnAddress = new Address("Prins Willem Alexanderplein 5, 6291 GZ Vaals, Niederlande");
    private final BigDecimal albertHeijnLatitude = BigDecimal.valueOf(50.772877629426176);
    private final BigDecimal albertHeijnLongitude = BigDecimal.valueOf(6.0209963286457375);

    private final Location aachenerNobis = new Location(aachenerNobisAddress, aachenerNobisLatitude, aachenerNobisLongitude);
    private final Location aachenerDom = new Location(aachenerDomAddress, aachenerDomLatitude, aachenerDomLongitude);
    private final Location albertHeijn = new Location(albertHeijnAddress, albertHeijnLatitude, albertHeijnLongitude);
    @Test
    public void testLocationWithinSpecifiedRadius() {
        assertTrue(aachenerNobis.isWithinRadiusOf(aachenerDom, 10));
        assertFalse(aachenerNobis.isWithinRadiusOf(albertHeijn, 1));
    }
}
