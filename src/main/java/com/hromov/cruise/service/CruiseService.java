package com.hromov.cruise.service;

import com.hromov.cruise.model.Cruise;

import java.util.List;

public interface CruiseService {
    List<Cruise> getCruiseList();

    Cruise findCruiseById(Long cruiseId);

    Cruise createCruise(Cruise cruise);

    Cruise updateCruise(Cruise cruise);

    void deleteCruise(Long cruiseId);
}
