package com.corekcioglu.weatherobservations.service;

import com.corekcioglu.weatherobservations.exception.NotFoundException;
import com.corekcioglu.weatherobservations.model.Balloon;
import com.corekcioglu.weatherobservations.model.Observation;
import com.corekcioglu.weatherobservations.repository.BalloonRepository;
import com.corekcioglu.weatherobservations.repository.ObservationRepository;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalloonService {

    private final BalloonRepository repository;
    private final ObservationRepository observationRepository;

    @Transactional(readOnly = true)
    public Balloon findById(@NonNull Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Balloon", id));
    }

    @Transactional
    public Balloon createBalloon() {
        return repository.save(new Balloon());
    }

    @Transactional
    public void addObservation(@NonNull Balloon balloon, Observation observation) {
        Optional<Observation> fromObservation = observationRepository
            .findFirstByBalloonAndTimestampIsLessThanEqualOrderByTimestampDesc(balloon, observation.getTimestamp());
        Optional<Observation> toObservation = observationRepository
            .findFirstByBalloonAndTimestampIsGreaterThanEqualOrderByTimestampAsc(balloon, observation.getTimestamp());
        calculateTotalDistance(balloon, observation, fromObservation, toObservation);
        balloon.addNewTemperature(observation.getTemperature());

        observation.setBalloon(balloon);
        observationRepository.save(observation);
        repository.save(balloon);

        log.info("Added observation to balloon; balloonId: {}", balloon.getId());
    }

    private void calculateTotalDistance(
        Balloon balloon,
        Observation currentObservation,
        Optional<Observation> fromObservation,
        Optional<Observation> toObservation
    ) {
        if (fromObservation.isPresent()) {
            balloon.addToTotalDistance(currentObservation.getLocation().distance(fromObservation.get().getLocation()));
        }
        if (toObservation.isPresent()) {
            balloon.addToTotalDistance(currentObservation.getLocation().distance(toObservation.get().getLocation()));
        }
        if (toObservation.isPresent() && fromObservation.isPresent()) {
            balloon.addToTotalDistance(-fromObservation.get().getLocation().distance(toObservation.get().getLocation()));
        }
    }
}
