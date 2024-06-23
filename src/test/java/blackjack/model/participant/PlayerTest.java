package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    public static Stream<Arguments> provideForHitTest() {
        return Stream.of(
                // 일반
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.SIX),
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        false
                ),
                // 블랙잭
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        true
                ),
                // 버스트
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.SPADE, Denomination.KING),
                                new Card(Suit.SPADE, Denomination.TEN)
                        ),
                        true
                )
        );
    }

    private void stayIfNotFinished (Participant participant) {
        if (participant.isFinished()) {
            return;
        }
        participant.stay();
    }

    public static Stream<Arguments> provideForProfitTest() {
        return Stream.of(
                // 플레이어, 딜러 블랙잭
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        0
                ),
                // 플레이어 블랙잭
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        1500
                ),
                // 플레이어 승리
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        1000
                ),
                // 플레이어, 딜러 무승부
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        0
                ),
                // 딜러 승리
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        -1000
                )
        );
    }

    @Test
    void stayTest() {
        Player player = new Player("플레이어", 1000);

        player.hit(new Card(Suit.CLOVER, Denomination.ACE));
        assertThat(player.state.isFinished()).isFalse();

        player.stay();
        assertThat(player.state.isFinished()).isTrue();
    }

    @Test
    void getCardsTest() {
        Player player = new Player("플레이어", 1000);

        player.hit(new Card(Suit.CLOVER, Denomination.ACE));
        player.hit(new Card(Suit.SPADE, Denomination.QUEEN));

        Cards expect = new Cards();
        expect.add(new Card(Suit.CLOVER, Denomination.ACE));
        expect.add(new Card(Suit.SPADE, Denomination.QUEEN));

        assertThat(player.getCards()).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideForHitTest")
    void hitTest(List<Card> cards, boolean expect) {
        Player player = new Player("플레이어", 1000);

        cards.forEach(player::hit);
        assertThat(player.state.isFinished()).isEqualTo(expect);
    }

    @Test
    void getNameTest() {
        Player player = new Player("플레이어", 1000);

        assertThat(player.getName()).isEqualTo("플레이어");
    }

    @ParameterizedTest
    @MethodSource("provideForProfitTest")
    void profitTest(List<Card> playerCards, List<Card> dealerCards, int expect) {
        Player player = new Player("플레이어", 1000);
        Dealer dealer = new Dealer();

        playerCards.forEach(player::hit);
        dealerCards.forEach(dealer::hit);

//        stayIfNotFinished(player);
//        stayIfNotFinished(dealer);

        System.out.println("플레이어 점수:" + player.getScore());
        System.out.println("딜러 점수:" + dealer.getScore());
        System.out.println("플레이어 isFinished:" + player.isFinished());
        System.out.println("딜러 isFinished:" + dealer.isFinished());

        stayIfNotFinished(player);
        stayIfNotFinished(dealer);

        assertThat(player.profit(dealer)).isEqualTo(expect);
    }

}
