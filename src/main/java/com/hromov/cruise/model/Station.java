package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "station_id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
}
