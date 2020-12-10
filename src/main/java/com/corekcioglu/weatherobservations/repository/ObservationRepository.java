package com.corekcioglu.weatherobservations.repository;

import com.corekcioglu.weatherobservations.model.Balloon;
import com.corekcioglu.weatherobservations.model.Observation;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    Optional<Observation> findFirstByBalloonAndTimestampIsGreaterThanEqualOrderByTimestampAsc(
        Balloon balloon, Instant timestamp);

    Optional<Observation> findFirstByBalloonAndTimestampIsLessThanEqualOrderByTimestampDesc(
        Balloon balloon, Instant timestamp);
}
