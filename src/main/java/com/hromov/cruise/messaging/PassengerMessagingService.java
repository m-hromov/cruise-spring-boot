package com.hromov.cruise.messaging;

import com.hromov.cruise.model.Passenger;

public interface PassengerMessagingService {
    void sendPassenger(Passenger passenger);
}
