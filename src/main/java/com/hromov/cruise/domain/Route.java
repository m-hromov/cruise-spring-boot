package com.hromov.cruise.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "route")
public class Route implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;
    @Id
    @OneToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @Column(name = "order_number")
    private Integer orderNumber;

    public Cruise getCruise() {
        return cruise;
    }

    public Route setCruise(Cruise cruise) {
        this.cruise = cruise;
        return this;
    }

    public Station getStation() {
        return station;
    }

    public Route setStation(Station station) {
        this.station = station;
        return this;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Route setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }
}
