package io.github.mateusztbh.transportappv3.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findALlByCard_Id(Integer card_id);

    @Query(value = "SELECT MAX(departure_counter) FROM trip", nativeQuery = true)
    int findMax();

    int findFirstByOrderByDepartureCounter();

    /*List<Trip> findByCard_Id(Integer card_id);*/

    Trip findByCard_Id(Integer id);
}
