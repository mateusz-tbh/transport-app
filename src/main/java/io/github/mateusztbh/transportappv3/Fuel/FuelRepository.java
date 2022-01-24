package io.github.mateusztbh.transportappv3.Fuel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuelRepository extends JpaRepository<Fuel, Integer> {

    @Query(value = "SELECT SUM(refueling_quantity) FROM fuel", nativeQuery = true)
    Integer sumFuel();

    List<Fuel> findAllByCard_Id(Integer card_id);
}
