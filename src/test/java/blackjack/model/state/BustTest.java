package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.state.implement.Bust;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BustTest {

    @Test
    void profitTest() {
        Cards cards = new Cards();
        double profit = new Bust(cards).profit(1000);

        assertThat(profit).isEqualTo(-1000);
    }
}
