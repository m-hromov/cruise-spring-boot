package com.hromov.cruise.service;

import com.hromov.cruise.model.Cruise;

import java.util.List;

public interface CruiseService {
    List<Cruise> getCruiseList();

    Cruise findCruiseById(Long cruiseId);

    void createCruise(Cruise cruise);

    void updateCruise(Cruise cruise);

    void deleteCruise(Long cruiseId);
}
