package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Ship;
import com.hromov.cruise.repository.ShipRepository;
import com.hromov.cruise.service.ShipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    private ShipRepository shipRepository;

    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public List<Ship> getShipList() {
        return (List<Ship>) shipRepository.findAll();
    }
}
