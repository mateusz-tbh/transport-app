package io.github.mateusztbh.transportappv3.Counters;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountersRepository extends JpaRepository<Counters, Integer> {

    List<Counters> findALlByCard_Id(Integer card_id);

    Counters findByCard_Id(Integer card_id);
}
