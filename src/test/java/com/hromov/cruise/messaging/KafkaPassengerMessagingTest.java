package com.hromov.cruise.messaging;

import com.hromov.cruise.listener.PassengerKafkaListener;
import com.hromov.cruise.model.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = "cruise.passenger")
class KafkaPassengerMessagingTest {
    @Autowired
    private PassengerMessagingService provider;
    @Autowired
    private PassengerKafkaListener listener;

    @Test
    void sendingWithEmbeddedKafka() throws InterruptedException {
        Passenger passenger = Passenger.builder()
                .firstName("testUser")
                .phone("testPhone")
                .build();

        provider.sendPassenger(passenger);

        Thread.sleep(1000);
        Passenger received = listener.getReceived();
        assertEquals(passenger, received);
    }
}