package com.hromov.cruise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "passenger_id")
    private Long id;
    @Column(name = "first_name")
    @Pattern(regexp = "^[a-zA-Z ,.'-]+$")
    private String firstName;
    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Z ,.'-]+$")
    private String lastName;
    @Column(name = "phone")
    @Pattern(regexp = "\\+?\\(?\\d{2,4}\\)?[\\d\\s-]{3,}")
    private String phone;
    @Column(name = "money")
    @DecimalMin("0")
    private BigDecimal money;
    @Column(name = "document_path")
    private String documentPath;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
