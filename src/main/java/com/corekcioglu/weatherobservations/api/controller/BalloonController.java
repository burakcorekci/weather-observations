package com.corekcioglu.weatherobservations.api.controller;

import com.corekcioglu.weatherobservations.dto.BalloonResponse;
import com.corekcioglu.weatherobservations.model.Balloon;
import com.corekcioglu.weatherobservations.service.BalloonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BalloonController {

    private final BalloonService service;

    @GetMapping("/balloon/{balloonId}")
    public BalloonResponse getBalloon(@PathVariable Long balloonId) {
        Balloon balloon = service.findById(balloonId);
        return BalloonResponse.fromBalloon(balloon);
    }

    @PostMapping("/balloon")
    public BalloonResponse postBalloon() {
        Balloon balloon = service.createBalloon();
        return BalloonResponse.fromBalloon(balloon);
    }
}
