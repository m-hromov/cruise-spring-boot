package com.hromov.cruise.service;

import com.hromov.cruise.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> getPassengerList();

    Passenger getPassengerById(Long id);

    void signUp(Passenger passenger);
}
