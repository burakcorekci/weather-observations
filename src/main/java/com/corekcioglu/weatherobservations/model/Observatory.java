package com.corekcioglu.weatherobservations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_observatory")
public class Observatory {

    @Id
    @Column(name = "id")
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name = "temperature")
    private Temperature temperature;
    @Enumerated(EnumType.STRING)
    @Column(name = "distance")
    private Distance distance;

    public static Observatory defaultObservatoryWithId(String id) {
        return new Observatory(id, Temperature.KELVIN, Distance.KILOMETER);
    }
}
