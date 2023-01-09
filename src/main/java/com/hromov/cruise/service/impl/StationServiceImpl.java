package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Station;
import com.hromov.cruise.repository.StationRepository;
import com.hromov.cruise.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    @Override
    public List<Station> getStationList() {
        return stationRepository.findAll();
    }
}
