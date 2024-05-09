package blackjack.model.card;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import blackjack.model.card.Number;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NumberTest {
    @Test
    void sum() {
        int sum = Number.sum(Arrays.asList(
                Number.ACE,
                Number.JACK,
                Number.FOUR
        ));

        assertThat(sum).isEqualTo(15);
    }

    @Test
    void draw() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 100; i++) {
                Number.pick();
            }
        });
    }

    @Test
    void getValue() {
        assertThat(Number.ACE.getValue()).isEqualTo(1);
        assertThat(Number.JACK.getValue()).isEqualTo(10);
    }
}
