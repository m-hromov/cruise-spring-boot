package com.hromov.cruise.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "cruise")
public class Cruise implements Serializable {
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
    @Column(name = "days_total")

    private Integer daysTotal;
    @Column(name = "price")

    private BigDecimal price;
    @Column(name = "description")

    private String description;
    @Column(name = "tickets_purchased")

    private Integer ticketsPurchased;
    @OneToMany(mappedBy = "cruise")
    @OrderBy("orderNumber ASC")
    private List<Route> stationList;

    public Long getId() {
        return id;
    }

    public Cruise setId(long id) {
        this.id = id;
        return this;
    }

    public LocalTime getTimeDeparture() {
        return timeDeparture;
    }

    public Cruise setTimeDeparture(LocalTime timeDeparture) {
        this.timeDeparture = timeDeparture;
        return this;
    }

    public LocalDate getDateDeparture() {
        return dateDeparture;
    }

    public Cruise setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
        return this;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public Cruise setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
        return this;
    }

    public Integer getDaysTotal() {
        return daysTotal;
    }

    public Cruise setDaysTotal(int daysTotal) {
        this.daysTotal = daysTotal;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Cruise setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Cruise setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getTicketsPurchased() {
        return ticketsPurchased;
    }

    public Cruise setTicketsPurchased(Integer ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
        return this;
    }

    public List<Route> getStationList() {
        return stationList;
    }

    public Cruise setStationList(List<Route> stationList) {
        this.stationList = stationList;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Cruise))
            return false;
        Cruise cruise = (Cruise) obj;
        return cruise.getId() == id;
    }

}
