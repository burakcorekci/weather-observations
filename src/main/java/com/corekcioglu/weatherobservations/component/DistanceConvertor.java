package com.corekcioglu.weatherobservations.component;

import com.corekcioglu.weatherobservations.model.Observation.Location;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DistanceConvertor {

    public static Double fromMilesToKilometers(Double miles) {
        return miles * 1.609344;
    }

    public static Location fromMilesToKilometers(Location location) {
        location.setX(fromMilesToKilometers(location.getX()));
        location.setY(fromMilesToKilometers(location.getY()));
        return location;
    }

    public static Double fromMetersToKiloMeters(Double meters) {
        return meters / 1000;
    }

    public static Location fromMetersToKiloMeters(Location location) {
        location.setX(fromMetersToKiloMeters(location.getX()));
        location.setY(fromMetersToKiloMeters(location.getY()));
        return location;
    }
}
