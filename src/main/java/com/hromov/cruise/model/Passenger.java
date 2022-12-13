package com.hromov.cruise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "money")
    private BigDecimal money;
    @Column(name = "document_path")
    private String documentPath;
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private User user;
}
