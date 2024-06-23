package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomTest {
    @Test
    void createZeroToMaxTest() {
        for (int i = 0; i < 100; i++) {
            assertThat(Random.createZeroToMax(4))
                    .isGreaterThanOrEqualTo(0)
                    .isLessThanOrEqualTo(3);
        }
    }
}
