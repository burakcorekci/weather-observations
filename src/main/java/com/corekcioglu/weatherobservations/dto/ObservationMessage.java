package com.corekcioglu.weatherobservations.dto;

import com.corekcioglu.weatherobservations.model.Balloon;
import com.corekcioglu.weatherobservations.model.Observation;
import com.corekcioglu.weatherobservations.model.Observation.Location;
import com.corekcioglu.weatherobservations.model.Observatory;
import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ObservationMessage {

    private Long balloonId;
    private Instant timestamp;
    private Location location;
    private Double temperature;
    private String observatoryId;

    public boolean validate() {
        return Objects.nonNull(balloonId)
            && Objects.nonNull(timestamp)
            && location.validate()
            && Objects.nonNull(temperature)
            && StringUtils.isNotBlank(observatoryId);
    }

    public Observation toObservation(Balloon balloon, Observatory observatory) {
        return new Observation(null, timestamp, location, temperature, balloon, observatory);
    }
}
