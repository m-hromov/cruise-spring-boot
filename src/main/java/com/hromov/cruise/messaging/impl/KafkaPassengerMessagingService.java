package com.hromov.cruise.messaging.impl;

import com.hromov.cruise.messaging.PassengerMessagingService;
import com.hromov.cruise.model.Passenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaPassengerMessagingService implements PassengerMessagingService {
    private final KafkaTemplate<String, Passenger> kafkaTemplate;

    @Override
    public void sendPassenger(Passenger passenger) {
        log.info("Sending {} passenger to cruise.passenger topic", passenger);
        kafkaTemplate.send("cruise.passenger", passenger);
    }
}
