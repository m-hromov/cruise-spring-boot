package com.hromov.cruise.service.impl;

import com.hromov.cruise.domain.Station;
import com.hromov.cruise.repository.StationRepository;
import com.hromov.cruise.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getStationList() {
        return (List<Station>) stationRepository.findAll();
    }
}
