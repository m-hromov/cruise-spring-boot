package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
}
