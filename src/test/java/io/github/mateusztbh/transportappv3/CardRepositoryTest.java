package io.github.mateusztbh.transportappv3;

import io.github.mateusztbh.transportappv3.Card.Card;
import io.github.mateusztbh.transportappv3.Card.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CardRepositoryTest {

    @Autowired
    private CardRepository repository;

    @Test
    public void testCreateCard() {
        Card saveCard = repository.save(new Card("Karta 1"));

        assertThat(saveCard.getId()).isGreaterThan(0);
    }
}
