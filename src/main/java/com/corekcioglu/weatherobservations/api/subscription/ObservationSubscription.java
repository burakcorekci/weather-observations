package com.corekcioglu.weatherobservations.api.subscription;

import com.corekcioglu.weatherobservations.component.ObservationMessageResolver;
import com.corekcioglu.weatherobservations.component.ObservationNormalizer;
import com.corekcioglu.weatherobservations.dto.ObservationMessage;
import com.corekcioglu.weatherobservations.model.Balloon;
import com.corekcioglu.weatherobservations.model.Observation;
import com.corekcioglu.weatherobservations.model.Observatory;
import com.corekcioglu.weatherobservations.service.BalloonService;
import com.corekcioglu.weatherobservations.service.ObservatoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObservationSubscription {

    private final ObservatoryService observatoryService;
    private final BalloonService balloonService;

    @RabbitListener(queues = "observation")
    public void onMessage(String messageText) {
        try {
            log.debug("Processing ObservationMessage; message: {}", messageText);
            ObservationMessage message = ObservationMessageResolver.resolve(messageText);
            if (!message.validate()) {
                log.warn("ObservationMessage is not valid; message: {}", messageText);
                return;
            }

            Balloon balloon = balloonService.findById(message.getBalloonId());
            Observatory observatory = observatoryService.findById(message.getObservatoryId());
            ObservationNormalizer.normalize(message, observatory);

            Observation observation = message.toObservation(balloon, observatory);
            balloonService.addObservation(balloon, observation);

            log.info("Processed ObservationMessage");
        } catch (Exception e) {
            log.error("Exception occurred during ObservationMessage processing; message: {}",
                messageText, e);
        }
    }
}
