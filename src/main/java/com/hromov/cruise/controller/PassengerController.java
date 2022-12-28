package com.hromov.cruise.controller;

import com.hromov.cruise.messaging.impl.KafkaPassengerMessagingService;
import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;
    private final RestTemplate restTemplate;
    private final KafkaPassengerMessagingService kafkaPassengerMessagingService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Passenger> loadAllPassengers() {
        return Arrays.asList(
                Objects.requireNonNull(
                        restTemplate.getForObject("http://localhost:8081/passenger/alll",
                                Passenger[].class)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/alll")
    public List<Passenger> loadAlPassengers() {
        return passengerService.getPassengerList();
    }

    @PostAuthorize("hasAuthority('ADMIN') || " +
            "returnObject.user.username == authentication.name")
    @GetMapping("/{id}")
    public Passenger loadPassengerById(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        kafkaPassengerMessagingService.sendPassenger(passenger);
        return passenger;
    }
}
