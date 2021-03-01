package com.melisdogan.catapult_labs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "meter_reading")
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private Integer reading;
    @ManyToOne
    @JoinColumn(name="meter_id")
    @JsonBackReference
    private Meter meter;

    public MeterReading(Long id, Long timestamp, Integer reading, Meter meter) {
        this.id = id;
        this.timestamp = timestamp;
        this.reading = reading;
        this.meter = meter;
    }

    public MeterReading() {

    }
}
