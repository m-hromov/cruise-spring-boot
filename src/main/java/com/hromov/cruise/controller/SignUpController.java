package com.hromov.cruise.controller;

import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sign_up")
@RequiredArgsConstructor
@Log4j2
public class SignUpController {
    private final PassengerService passengerService;

    @GetMapping
    public ModelAndView loadPage() {
        return new ModelAndView("signUp");
    }

    @PostMapping
    public ResponseEntity<Passenger> signUp(@Valid @RequestBody Passenger passenger) {
        Passenger registered = passengerService.signUp(passenger);
        log.info("Passenger was registred '{}'", registered);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }
}
