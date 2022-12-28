package com.hromov.cruise.messaging.impl;

import com.hromov.cruise.messaging.PassengerMessagingService;
import com.hromov.cruise.model.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPassengerMessagingService implements PassengerMessagingService {
    private final KafkaTemplate<String, Passenger> kafkaTemplate;

    @Override
    public void sendPassenger(Passenger passenger) {
        kafkaTemplate.send("cruise.passenger", passenger);
    }
}
