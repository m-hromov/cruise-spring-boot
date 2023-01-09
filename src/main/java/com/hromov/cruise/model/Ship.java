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
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ship_id")
    private long id;
    @Column(name = "passenger_capacity")
    private int passengerCapacity;
    @Column(name = "name")
    private String name;
    @Column(name = "photo_path")
    private String photoPath;
}
