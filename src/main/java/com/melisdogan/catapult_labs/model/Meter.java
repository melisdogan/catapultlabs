package com.melisdogan.catapult_labs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "meter")
    @JsonManagedReference
    List<MeterReading> readingList;
    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference
    Client client;
    public Meter(){}
    public Meter(Long id, List<MeterReading> readingList) {
        this.id = id;
        this.readingList = readingList;
    }
}
