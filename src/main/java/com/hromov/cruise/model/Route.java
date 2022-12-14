package com.hromov.cruise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "route")
public class Route {
    @Id
    @ManyToOne
    @JoinColumn(name = "cruise_id")
    @JsonBackReference
    private Cruise cruise;
    @Id
    @OneToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @Column(name = "order_number")
    private Integer orderNumber;
}
