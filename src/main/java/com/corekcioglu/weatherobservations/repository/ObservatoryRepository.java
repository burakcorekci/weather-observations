package com.corekcioglu.weatherobservations.repository;

import com.corekcioglu.weatherobservations.model.Observatory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservatoryRepository extends JpaRepository<Observatory, String> {
}
