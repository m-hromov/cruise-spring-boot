package com.hromov.cruise.controller;

import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Passenger> loadAllPassengers() {
        return passengerService.getPassengerList();
    }

    @PostAuthorize("hasAuthority('ADMIN') || " +
            "returnObject.user.username == authentication.name")
    @GetMapping("/{id}")
    public Passenger loadPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }
}
