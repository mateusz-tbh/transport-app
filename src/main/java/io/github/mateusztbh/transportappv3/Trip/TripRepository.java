package io.github.mateusztbh.transportappv3.Trip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findALlByCard_Id(Integer card_id);
}
