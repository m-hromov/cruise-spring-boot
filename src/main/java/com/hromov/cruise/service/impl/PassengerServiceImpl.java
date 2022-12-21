package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.Passenger;
import com.hromov.cruise.model.User;
import com.hromov.cruise.repository.PassengerRepository;
import com.hromov.cruise.repository.UserRepository;
import com.hromov.cruise.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(Passenger passenger) {
        User user = passenger.getUser();
        user.setRole(User.Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(passenger.getUser());
        passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> getPassengerList() {
        return passengerRepository.findAll();
    }
}
