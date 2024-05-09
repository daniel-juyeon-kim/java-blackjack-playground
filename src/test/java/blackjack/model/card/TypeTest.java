package blackjack.model.card;

import blackjack.model.card.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TypeTest {

    @Test
    void pick() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; i++) {
                Type.pick();
            }
        });
    }

    @Test
    void getName() {
        assertThat(Type.SPADE.getName()).isEqualTo("스페이드");
    }
}
