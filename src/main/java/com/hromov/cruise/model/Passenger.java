package com.hromov.cruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long id;
    @Column(name = "first_name")
    @Pattern(regexp = "^[a-z ,.'-]+$")
    private String firstName;
    @Column(name = "last_name")
    @Pattern(regexp = "^[a-z ,.'-]+$")
    private String lastName;
    @Column(name = "phone")
    @Pattern(regexp = "\\+?\\(?\\d{2,4}\\)?[\\d\\s-]{3,}")
    private String phone;
    @Column(name = "money")
    @Pattern(regexp = "^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")
    private BigDecimal money;
    @Column(name = "document_path")
    private String documentPath;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
