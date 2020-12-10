package com.corekcioglu.weatherobservations.model;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_observation")
public class Observation {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "observation_id_sequence", sequenceName = "seq_observation_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "observation_id_sequence")
    private Long id;
    @Column(name = "observation_time")
    private Instant timestamp;
    @Embedded
    private Location location;
    @Column(name = "temperature")
    private Double temperature;
    @ManyToOne
    @JoinColumn(name = "balloon_id")
    private Balloon balloon;
    @ManyToOne
    @JoinColumn(name = "observatory_id")
    private Observatory observatory;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Location {
        @Column(name = "x")
        private Double x;
        @Column(name = "y")
        private Double y;

        public boolean validate() {
            return Objects.nonNull(x)
                && Objects.nonNull(y);
        }

        public double distance(Location location) {
            double xDiff = this.x - location.getX();
            double yDiff = this.y - location.getY();
            return Math.pow(Math.pow(xDiff, 2) + Math.pow(yDiff, 2), 0.5);
        }
    }
}
