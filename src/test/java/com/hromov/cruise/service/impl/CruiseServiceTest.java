package com.hromov.cruise.service.impl;

import com.hromov.cruise.exception.CruiseNotFoundException;
import com.hromov.cruise.model.Cruise;
import com.hromov.cruise.model.Ship;
import com.hromov.cruise.model.Station;
import com.hromov.cruise.repository.CruiseRepository;
import com.hromov.cruise.service.CruiseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CruiseServiceTest {
    @Mock
    private CruiseRepository cruiseRepository;
    private CruiseService cruiseService;

    @BeforeEach
    void setUp() {
        cruiseService = new CruiseServiceImpl(cruiseRepository);
    }

    @Test
    void testGetsCruiseList() {
        Cruise cruise1 = Cruise.builder()
                .id(1L)
                .description("test1")
                .stationList(List.of(new Station()))
                .ship(new Ship())
                .build();
        Cruise cruise2 = Cruise.builder()
                .id(2L)
                .description("test2")
                .stationList(List.of(new Station()))
                .ship(new Ship())
                .build();
        List<Cruise> expected = List.of(cruise1, cruise2);
        given(cruiseRepository.findAll()).willReturn(expected);

        List<Cruise> actual = cruiseService.getCruiseList();
        verify(cruiseRepository, times(1)).findAll();
        assertNotNull(actual);
        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetsEmptyCruiseListIfNoCruisesFound() {
        given(cruiseRepository.findAll()).willReturn(Collections.emptyList());

        List<Cruise> actual = cruiseService.getCruiseList();
        verify(cruiseRepository, times(1)).findAll();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testThrowsWhenCruiseIsNotFoundById() {
        given(cruiseRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(CruiseNotFoundException.class, () -> cruiseService.findCruiseById(0L));
        verify(cruiseRepository, times(1)).findById(any());
    }

    @Test
    void testCreatesCruiseAndSetsAnId() {
        Cruise cruise = Cruise.builder()
                .description("test1")
                .stationList(Collections.emptyList())
                .ship(new Ship())
                .build();
        Cruise cruise1 = Cruise.builder()
                .id(1L)
                .description("test1")
                .stationList(Collections.emptyList())
                .ship(new Ship())
                .build();
        given(cruiseRepository.save(cruise)).willReturn(cruise1);

        Cruise actual = cruiseService.createCruise(cruise);
        verify(cruiseRepository, times(1)).save(cruise);
        assertNotNull(actual);
        assertEquals(cruise1, actual);
    }

    @Test
    void testUpdatesCruise() {
        Cruise cruise = Cruise.builder()
                .id(1L)
                .description("test1")
                .stationList(Collections.emptyList())
                .ship(new Ship())
                .build();
        given(cruiseRepository.save(cruise)).willReturn(cruise);

        Cruise actual = cruiseService.createCruise(cruise);
        verify(cruiseRepository, times(1)).save(cruise);
        assertNotNull(actual);
        assertEquals(cruise, actual);
    }

    @Test
    void deleteCruise() {
        assertDoesNotThrow(() -> cruiseService.deleteCruise(10L));
    }
}