package com.hromov.cruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cruises")
@RestResource(rel = "CRUISES", path = "CrUiSeS")
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @NotNull
    @Column(name = "description")
    private String description;
    @Column(name = "tickets_purchased")
    private Integer ticketsPurchased;
    @OneToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;
    @ManyToMany
    @JoinTable(name = "cruise_station",
            joinColumns = @JoinColumn(name = "cruise_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id"))
    @OrderColumn(name = "order_number")
    private List<Station> stationList;
}
