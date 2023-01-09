package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Station;
import com.hromov.cruise.repository.StationRepository;
import com.hromov.cruise.service.StationService;
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
class StationServiceTest {
    @Mock
    private StationRepository stationRepository;
    private StationService stationService;

    @BeforeEach
    void setUp() {
        stationService = new StationServiceImpl(stationRepository);
    }

    @Test
    void testGetsStationList() {
        Station station1 = Station.builder()
                .id(1L)
                .city("test")
                .country("test")
                .build();
        Station station2 = Station.builder()
                .id(2L)
                .city("test")
                .country("test")
                .build();
        List<Station> expected = List.of(station1, station2);
        given(stationRepository.findAll()).willReturn(expected);

        List<Station> actual = stationService.getStationList();
        assertNotNull(actual);
        assertIterableEquals(expected, actual);
    }
}