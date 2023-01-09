package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ticket_id")
    private Long id;
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
