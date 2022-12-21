package com.hromov.cruise.controller;

import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.service.PassengerService;
import com.hromov.cruise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sign_up")
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;
    private final PassengerService passengerService;

    @GetMapping
    public ModelAndView loadPage() {
        return new ModelAndView("signUp");
    }

    @PostMapping
    public ResponseEntity<Passenger> signUp(@RequestBody Passenger passenger) {
        passengerService.signUp(passenger);
        return new ResponseEntity<>(passenger, HttpStatus.CREATED);
    }
}
