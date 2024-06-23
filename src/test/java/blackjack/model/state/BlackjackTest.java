package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.state.implement.Blackjack;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BlackjackTest {
    private final Cards cards = new Cards();

    @Test
    void isFinishedTest() {
        assertThat(new Blackjack(cards).isFinished()).isTrue();
    }

    @Test
    void drawTest() {
        assertThatThrownBy(() -> {
            Card card = new Card(Suit.CLOVER, Denomination.NINE);
            new Blackjack(cards).draw(card);
        }).isInstanceOf(IllegalCallerException.class)
                .hasMessage("이미 종료되었습니다.");
    }

    @Test
    void stayTest() {
        assertThatThrownBy(() -> {
            new Blackjack(cards).stay();
        }).isInstanceOf(IllegalCallerException.class)
                .hasMessage("이미 종료되었습니다.");
    }

    @Test
    void profitTest() {
        double profit = new Blackjack(cards).profit(1000);

        assertThat(profit).isEqualTo(1500);
    }
}
