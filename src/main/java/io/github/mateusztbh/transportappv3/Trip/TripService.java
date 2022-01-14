package io.github.mateusztbh.transportappv3.Trip;

import org.springframework.stereotype.Service;

@Service
public class TripService {
    TripRepository tripRepository;

    public int course(Trip trip) {
        int one = trip.getDepartureCounter();
        int two = trip.getArrivalCounter();
        return two - one;
    }
}
