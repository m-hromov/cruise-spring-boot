package com.hromov.cruise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @Column(name = "ship_id")
    private long id;
    @Column(name = "passenger_capacity")
    private int passengerCapacity;
    @Column(name = "name")
    private String name;
    @Column(name = "photo_path")
    private String photoPath;
}
