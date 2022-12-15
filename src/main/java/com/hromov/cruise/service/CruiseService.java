package com.hromov.cruise.service;

import com.hromov.cruise.model.Cruise;

import java.util.List;

public interface CruiseService {
    List<Cruise> getCruiseList();

    void createCruise(Cruise cruise);

    Cruise findCruiseById(long id);
}
