package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "route")
public class Route {
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
}
