package com.hromov.cruise.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "station")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    public Long getId() {
        return id;
    }

    public Station setId(long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Station setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Station setCountry(String country) {
        this.country = country;
        return this;
    }
}
