package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private long id;
    @OneToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @OneToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;
    @Column(name = "paid")
    private boolean paid;
    @Column(name = "banned")
    private boolean banned;
    @Column(name = "confirmed")
    private boolean confirmed;
}
