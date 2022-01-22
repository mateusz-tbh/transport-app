package io.github.mateusztbh.transportappv3.Card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
    boolean existsByNumber(String number);
}
