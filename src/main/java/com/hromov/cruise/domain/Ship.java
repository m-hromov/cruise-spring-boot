package com.hromov.cruise.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ship")
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

    public long getId() {
        return id;
    }

    public Ship setId(long id) {
        this.id = id;
        return this;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public Ship setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        this.name = name;
        return this;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public Ship setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
        return this;
    }
}
