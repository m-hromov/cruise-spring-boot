package com.hromov.cruise.service;

import com.hromov.cruise.model.Passenger;

import java.util.List;

public interface PassengerService {
    void signUp(Passenger passenger);

    List<Passenger> getPassengerList();
}
