package com.hromov.cruise.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic passengerTopic() {
        return new NewTopic("cruise.passenger", 10, (short) 1);
    }
}
