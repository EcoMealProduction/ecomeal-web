package model.unit.restaurant;

import org.junit.jupiter.api.Test;

import static model.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationTest {

    @Test
    public void testLocationWithinSpecifiedRadius() {
        assertTrue(aachenerNobis.isWithinRadiusOf(aachenerDom, 10));
        assertFalse(aachenerNobis.isWithinRadiusOf(albertHeijn, 1));
    }
}
