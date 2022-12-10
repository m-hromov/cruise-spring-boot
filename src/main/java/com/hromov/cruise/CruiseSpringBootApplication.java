package com.hromov.cruise;

import com.hromov.cruise.domain.Cruise;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruiseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruiseSpringBootApplication.class, args);
    }

    @Bean
    List<Cruise> cruiseList() {
        List<Cruise> cruiseList = new ArrayList<>();
        cruiseList.add(new Cruise().setId(10)
                .setDescription("Description")
                .setPrice(BigDecimal.valueOf(100)));
        return cruiseList;
    }
}
