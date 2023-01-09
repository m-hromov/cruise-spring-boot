package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Ship;
import com.hromov.cruise.repository.ShipRepository;
import com.hromov.cruise.service.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ShipServiceTest {
    @Mock
    private ShipRepository shipRepository;
    private ShipService shipService;

    @BeforeEach
    void setUp() {
        shipService = new ShipServiceImpl(shipRepository);
    }

    @Test
    void testGetsShipList() {
        Ship ship = Ship.builder()
                .id(1L)
                .passengerCapacity(100)
                .photoPath("test")
                .name("test")
                .build();
        Ship ship2 = Ship.builder()
                .id(2L)
                .passengerCapacity(100)
                .photoPath("test")
                .name("test")
                .build();
        List<Ship> expected = List.of(ship, ship2);
        given(shipRepository.findAll()).willReturn(expected);

        List<Ship> actual = shipService.getShipList();
        assertNotNull(actual);
        assertIterableEquals(expected, actual);
    }
}