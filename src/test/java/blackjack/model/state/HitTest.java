package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.state.implement.Blackjack;
import blackjack.model.state.implement.Bust;
import blackjack.model.state.implement.Hit;
import blackjack.model.state.implement.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HitTest {

    private static Cards cards;

    private Cards createCards(List<Card> testCards) {
        Cards newCards = new Cards();
        testCards.forEach(newCards::add);

        return newCards;
    }

    @BeforeEach
    public void initializeCards() {
        cards = createCards(
                new ArrayList<>(
                        Arrays.asList(
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.KING)
                        )
                )
        );
    }

    public static Stream<Arguments> provideValueForDrawTest() {
        return Stream.of(
                Arguments.of(
                        new Card(Suit.CLOVER, Denomination.ACE),
                        Blackjack.class
                ),
                Arguments.of(
                        new Card(Suit.CLOVER, Denomination.TWO),
                        Bust.class
                )
        );
    }

    @Test
    void cardsTest() {
        Cards newCards = createCards(
                new ArrayList<>(
                        Arrays.asList(
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.KING)
                        )
                )
        );

        assertThat(new Hit(cards).cards().equals(newCards)).isTrue();
    }

    @Test
    void isFinishedTest() {
        assertThat(new Hit(cards).isFinished()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideValueForDrawTest")
    void drawTest(Card card, Class<State> expect) {
        assertThat(new Hit(cards).draw(card)).isInstanceOf(expect);
    }

    @Test
    void stayTest() {
        assertThat(new Hit(cards).stay()).isInstanceOf(Stay.class);
        assertThat(new Hit(cards).stay()).isNotInstanceOf(Blackjack.class);
    }

    @Test
    void profitTest() {
        Cards cards = new Cards();
        cards.add(new Card(Suit.CLOVER, Denomination.QUEEN));

        Hit hit = new Hit(cards);

        assertThatThrownBy(() -> {
            hit.profit(1000);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("드로우가 끝내지 않은 상태입니다.");
    }

}
