package com.corekcioglu.weatherobservations.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_balloon")
public class Balloon {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "balloon_id_sequence", sequenceName = "seq_balloon_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balloon_id_sequence")
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "balloon")
    private List<Observation> observations = new ArrayList<>();
    @Column(name = "min_temperature")
    private Double minTemperature;
    @Column(name = "max_temperature")
    private Double maxTemperature;
    @Column(name = "mean_temperature")
    private Double meanTemperature;
    @Column(name = "total_distance")
    private Double totalDistance = 0.0;
    @Column(name = "observation_count")
    private Long observationCount = 0L;
    @Version
    private Long version;

    public void addToTotalDistance(double distance) {
        this.totalDistance += distance;
    }
    
    public void addNewTemperature(Double temp) {
        double maxTemp = Optional.ofNullable(maxTemperature).orElse(Double.MIN_VALUE);
        double minTemp = Optional.ofNullable(minTemperature).orElse(Double.MAX_VALUE);
        double meanTemp = Optional.ofNullable(meanTemperature).orElse(0.0);

        if (temp > maxTemp) {
            setMaxTemperature(temp);
        }
        if (temp < minTemp) {
            setMinTemperature(temp);
        }

        this.observationCount += 1;
        setMeanTemperature((meanTemp * (observationCount - 1) + temp) / observationCount);
    }
}
