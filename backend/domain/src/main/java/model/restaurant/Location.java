package model.restaurant;

import lombok.NonNull;
import model.shared.Address;

import java.math.BigDecimal;

/**
 *  Represents a geographic location with an address and GPS coordinates (latitude and longitude).
 *  Validates coordinate bounds and provides geospatial calculations like distance checks.
 *
 * @param address Physical address associated with this location
 * @param latitude Latitude in decimal degrees, must be between -90 and 90
 * @param longitude Longitude in decimal degrees, must be between -180 and 180
 */
public record Location(
        @NonNull Address address,
        @NonNull BigDecimal latitude,
        @NonNull BigDecimal longitude) {

    /**
     * Validates the geographical bounds of latitude and longitude upon instantiation.
     * Latitude must be in [-90, 90], longitude in [-180, 180], as per WGS 84 standard.
     */
    public Location {
          if (latitude.compareTo(BigDecimal.valueOf(-90)) < 0 || latitude.compareTo(BigDecimal.valueOf(90)) > 0)
              throw new IllegalArgumentException("Latitude must be between -90 and 90.");

          if (longitude.compareTo(BigDecimal.valueOf(-180)) < 0 || longitude.compareTo(BigDecimal.valueOf(180)) > 0)
              throw new IllegalArgumentException("Longitude must be between -180 and 180.");
    }

    /**
     * Determines whether this location is within a specified radius (in kilometers) of another location.
     * Uses the Haversine formula to calculate great-circle distance between two points on Earth.
     *
     * @param other      the reference location to compare against
     * @param radiusInKm the radius threshold in kilometers
     * @return true if the distance between this and the other location is less than or equal to the radius
     */
    public boolean isWithinRadiusOf(Location other, double radiusInKm) {
        final int EARTH_RADIUS_KM = 6371;

        double lat1 = Math.toRadians(this.latitude.doubleValue());
        double lat2 = Math.toRadians(other.latitude.doubleValue());
        double deltaLat = lat2 - lat1;
        double deltaLon = Math.toRadians(other.longitude.doubleValue() - this.longitude.doubleValue());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * c;

        return distance <= radiusInKm;
    }
}
