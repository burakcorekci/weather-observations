package com.corekcioglu.weatherobservations.dto;

import com.corekcioglu.weatherobservations.model.Balloon;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BalloonResponse {

    private Long id;
    private Double minTemperature;
    private Double maxTemperature;
    private Double meanTemperature;
    private Double totalDistance;

    public static BalloonResponse fromBalloon(Balloon balloon) {
        return new BalloonResponse(
            balloon.getId(),
            balloon.getMinTemperature(),
            balloon.getMaxTemperature(),
            balloon.getMeanTemperature(),
            balloon.getTotalDistance());
    }
}
