package com.corekcioglu.weatherobservations.component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TemperatureConvertor {

    public static Double fromCelsiusToKelvin(Double celsius) {
        return celsius + 273.15;
    }

    public static Double fromFahrenheitToKelvin(Double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }
}
