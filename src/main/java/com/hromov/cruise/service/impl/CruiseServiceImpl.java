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
    public Cruise findCruiseById(long cruiseId) {
        return cruiseRepository.findById(cruiseId)
                .orElseThrow(CruiseNotFoundException::new);
    }

    @Override
    public void createCruise(Cruise cruise) {
        cruiseRepository.save(cruise);
    }

    @Override
    public void updateCruise(Cruise cruise) {
        cruiseRepository.deleteById(cruise.getId());
        createCruise(cruise);
    }

    @Override
    public void deleteCruise(long cruiseId) {
        cruiseRepository.deleteById(cruiseId);
    }
}
