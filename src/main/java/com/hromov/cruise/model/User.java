package com.hromov.cruise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
public class User {
    @Id
    private long id;
    private String email;
    private String password;
    private String passwordSalt;
    private String role;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public User setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }
}
