package blackjack.model.card;

import blackjack.model.card.Random;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomTest {
    @Test
    void createTest() {
        int end = 10;

        for (int i = 0; i < 10; i++) {
            int random = Random.create(end);

            assertThat(random).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(end);
        }
    }
}
