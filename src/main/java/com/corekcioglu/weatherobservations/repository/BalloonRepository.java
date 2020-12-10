package com.corekcioglu.weatherobservations.repository;

import com.corekcioglu.weatherobservations.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalloonRepository extends JpaRepository<Balloon, Long> {
}
