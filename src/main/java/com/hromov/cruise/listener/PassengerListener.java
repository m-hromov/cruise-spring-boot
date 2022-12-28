package com.hromov.cruise.listener;

import com.hromov.cruise.model.Passenger;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PassengerListener {
    @KafkaListener(id = "passenger", topics = "cruise.passenger")
    public void handle(Passenger passenger, ConsumerRecord<String, Passenger> record) {
        log.info("\nPassenger {}\nfrom partition {}, timestamp {}",
                passenger, record.partition(), record.timestamp());
    }
}
