package com.hromov.cruise.service.impl;

import com.hromov.cruise.exception.CruiseNotFoundException;
import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.repository.CruiseRepository;
import com.hromov.cruise.service.CruiseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CruiseServiceImpl implements CruiseService {
    private CruiseRepository cruiseRepository;

    public CruiseServiceImpl(CruiseRepository cruiseRepository) {
        this.cruiseRepository = cruiseRepository;
    }

    @Override
    public List<Cruise> getCruiseList() {
        return cruiseRepository.findAll();
    }

    @Override
    public void createCruise(Cruise cruise) {
        cruiseRepository.save(cruise);
    }

    @Override
    public Cruise findCruiseById(long id) throws CruiseNotFoundException {
        return cruiseRepository.findById(id).orElseThrow(CruiseNotFoundException::new);
    }
}
