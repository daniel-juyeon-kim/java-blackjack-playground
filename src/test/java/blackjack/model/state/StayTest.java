package blackjack.model.state;

import blackjack.model.card.Cards;
import blackjack.model.state.implement.Stay;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StayTest {

    @Test
    void profitTest() {
        Cards cards = new Cards();
        double profit = new Stay(cards).profit(1000);

        assertThat(profit).isEqualTo(1000);
    }

}
