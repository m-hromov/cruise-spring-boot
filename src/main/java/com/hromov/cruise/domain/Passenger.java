package com.hromov.cruise.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private BigDecimal money;
    private String documentPath;
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private User user;

    public long getId() {
        return id;
    }

    public Passenger setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Passenger setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Passenger setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Passenger setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Passenger setMoney(BigDecimal money) {
        this.money = money;
        return this;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public Passenger setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
        return this;
    }

    public User getUserAccount() {
        return user;
    }

    public Passenger setUserAccount(User user) {
        this.user = user;
        return this;
    }
}
