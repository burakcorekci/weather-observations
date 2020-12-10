package com.corekcioglu.weatherobservations.component;

import com.corekcioglu.weatherobservations.dto.ObservationMessage;
import com.corekcioglu.weatherobservations.model.Observatory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObservationNormalizer {

    public static ObservationMessage normalize(ObservationMessage message, Observatory observatory) {
        switch (observatory.getTemperature()) {
            case CELSIUS:
                message.setTemperature(
                    TemperatureConvertor.fromCelsiusToKelvin(message.getTemperature()));
                break;
            case FAHRENHEIT:
                message.setTemperature(
                    TemperatureConvertor.fromFahrenheitToKelvin(message.getTemperature()));
                break;
        }
        switch (observatory.getDistance()) {
            case METER:
                message.setLocation(DistanceConvertor.fromMetersToKiloMeters(message.getLocation()));
                break;
            case MILE:
                message.setLocation(DistanceConvertor.fromMilesToKilometers(message.getLocation()));
                break;
        }
        return message;
    }
}
