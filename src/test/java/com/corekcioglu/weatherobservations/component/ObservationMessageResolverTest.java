package com.corekcioglu.weatherobservations.component;

import static org.assertj.core.api.Assertions.assertThat;

import com.corekcioglu.weatherobservations.dto.ObservationMessage;
import com.corekcioglu.weatherobservations.model.Observation.Location;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

public class ObservationMessageResolverTest {

    @Test
    public void test() {
        final String messageString = "1|2014-12-31T13:44|10,5|243|AU";
        ObservationMessage message = ObservationMessageResolver.resolve(messageString);
        assertThat(message.getBalloonId()).isEqualTo(1L);
        assertThat(message.getTimestamp()).isEqualTo(LocalDateTime.of(2014, 12, 31, 13, 44).toInstant(
            ZoneOffset.UTC));
        assertThat(message.getLocation()).isEqualTo(new Location(10.0, 5.0));
        assertThat(message.getTemperature()).isEqualTo(243.0);
        assertThat(message.getObservatoryId()).isEqualTo("AU");
    }
}
