package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "cruise")
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cruise_id")
    private Long id;
    @Column(name = "time_departure")
    private LocalTime timeDeparture;
    @Column(name = "date_departure")
    private LocalDate dateDeparture;
    @Column(name = "date_arrival")
    private LocalDate dateArrival;
    @Column(name = "days_total", insertable = false)
    private Integer daysTotal;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "tickets_purchased")
    private Integer ticketsPurchased;
    @OneToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;
    @ManyToMany
    @JoinTable(name = "route",
            joinColumns = @JoinColumn(name = "cruise_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    @OrderColumn(name = "order_number")
    @ToString.Exclude
    private List<Station> stationList;
}
