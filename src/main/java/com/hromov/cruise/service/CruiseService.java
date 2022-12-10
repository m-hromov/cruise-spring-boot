package com.hromov.cruise.service;

import com.hromov.cruise.domain.Cruise;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CruiseService {
    List<Cruise> getCruiseList();
    void createCruise(Cruise cruise);
}
