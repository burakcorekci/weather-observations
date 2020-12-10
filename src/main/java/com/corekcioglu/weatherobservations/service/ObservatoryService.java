package com.corekcioglu.weatherobservations.service;

import com.corekcioglu.weatherobservations.model.Observatory;
import com.corekcioglu.weatherobservations.repository.ObservatoryRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObservatoryService {

    private final ObservatoryRepository repository;

    @Transactional
    public Observatory findById(@NonNull final String id) {
        Optional<Observatory> possibleObservatory = repository.findById(id);
        if (possibleObservatory.isEmpty()) {
            Observatory observatory = Observatory.defaultObservatoryWithId(id);
            repository.save(observatory);
            log.info("Created new observatory; id: {}", id);
            possibleObservatory = Optional.of(observatory);
        }
        return possibleObservatory.get();
    }
}
