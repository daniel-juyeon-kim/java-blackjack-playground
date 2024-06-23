package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SuitTest {
    @Test
    void findTest() {
        assertThat(Suit.find(0)).isEqualTo(Suit.SPADE);
        assertThat(Suit.find(3)).isEqualTo(Suit.HART);
    }

    @Test
    void getNameTest() {
        assertThat(Suit.SPADE.getName()).isEqualTo("스페이드");
    }
}
