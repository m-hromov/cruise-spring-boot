package com.hromov.cruise.service.impl;

import com.hromov.cruise.exception.PassengerNotFoundException;
import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.model.User;
import com.hromov.cruise.repository.PassengerRepository;
import com.hromov.cruise.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passengerService = new PassengerServiceImpl(passengerRepository, passwordEncoder);
    }

    @Test
    void testGetsPassengerById() {
        Passenger passenger = Passenger.builder()
                .id(1L)
                .phone("1231231233")
                .user(new User())
                .money(BigDecimal.valueOf(100))
                .build();
        given(passengerRepository.findById(passenger.getId()))
                .willReturn(Optional.of(passenger));

        Passenger actual = passengerService.getPassengerById(1L);
        verify(passengerRepository, times(1)).findById(1L);
        assertNotNull(actual);
        assertEquals(passenger, actual);
    }

    @Test
    void throwsIfPassengerNotFound() {
        given(passengerRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThrows(PassengerNotFoundException.class, () -> passengerService.getPassengerById(any()));
        verify(passengerRepository, times(1)).findById(any());
    }

    @Test
    void testGetsPassengerList() {
        Passenger passenger1 = Passenger.builder()
                .id(1L)
                .phone("1231231233")
                .user(new User())
                .money(BigDecimal.valueOf(100))
                .build();
        Passenger passenger2 = Passenger.builder()
                .id(2L)
                .phone("1231231233")
                .user(new User())
                .money(BigDecimal.valueOf(200))
                .build();
        List<Passenger> expected = List.of(passenger1, passenger2);
        given(passengerRepository.findAll())
                .willReturn(expected);

        List<Passenger> actual = passengerService.getPassengerList();
        verify(passengerRepository, times(1)).findAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    void signUp() {
        User user = User.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .credentialsNonExpired(true)
                .authorities(Collections.emptySet())
                .username("test")
                .password("passwordTest")
                .build();
        Passenger passenger = Passenger.builder()
                .firstName("Test")
                .lastName("TestLast")
                .documentPath("path")
                .money(BigDecimal.valueOf(100))
                .phone("1231231233")
                .user(user)
                .build();
        User expectedUser = User.builder()
                .id(1L)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .credentialsNonExpired(true)
                .authorities(Collections.emptySet())
                .username("test")
                .password("encodedPassword")
                .build();
        Passenger expectedPassenger = Passenger.builder()
                .id(1L)
                .firstName("Test")
                .lastName("TestLast")
                .documentPath("path")
                .money(BigDecimal.valueOf(100))
                .phone("1231231233")
                .user(expectedUser)
                .build();
        given(passengerRepository.save(passenger))
                .willReturn(expectedPassenger);
        given(passwordEncoder.encode("passwordTest"))
                .willReturn("encodedPassword");

        Passenger actual = passengerService.signUp(passenger);
        verify(passengerRepository, times(1)).save(passenger);
        verify(passwordEncoder, times(1)).encode("passwordTest");
        assertEquals(expectedPassenger, actual);
    }
}