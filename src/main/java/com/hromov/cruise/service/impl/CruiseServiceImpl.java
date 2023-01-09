package com.hromov.cruise.service.impl;

import com.hromov.cruise.exception.CruiseNotFoundException;
import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.repository.CruiseRepository;
import com.hromov.cruise.service.CruiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CruiseServiceImpl implements CruiseService {
    private final CruiseRepository cruiseRepository;

    @Override
    public List<Cruise> getCruiseList() {
        return cruiseRepository.findAll();
    }

    @Override
    public Cruise findCruiseById(Long cruiseId) {
        return cruiseRepository.findById(cruiseId)
                .orElseThrow(() -> new CruiseNotFoundException("Cruise '" + cruiseId + "' is not found"));
    }

    @Override
    public Cruise createCruise(Cruise cruise) {
        return cruiseRepository.save(cruise);
    }

    @Override
    public Cruise updateCruise(Cruise cruise) {
        return createCruise(cruise);
    }

    @Override
    public void deleteCruise(Long cruiseId) {
        cruiseRepository.deleteById(cruiseId);
    }
}
