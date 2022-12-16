package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Ship;
import com.hromov.cruise.repository.ShipRepository;
import com.hromov.cruise.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;

    @Override
    public List<Ship> getShipList() {
        return (List<Ship>) shipRepository.findAll();
    }
}
