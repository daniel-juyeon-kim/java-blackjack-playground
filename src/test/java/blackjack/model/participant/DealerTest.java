package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
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

public class DealerTest {

    private AbstractParticipant player0;
    private AbstractParticipant player1;
    private AbstractParticipant player2;
    private AbstractParticipant player3;
    private Dealer dealer;

    public static Stream<Arguments> provideForProfitTest() {
        return Stream.of(
                // 플레이어0: 버스트
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN)
                        ),
                        List.of(

                        ),
                        -500
                ),
                // 플레이어0: 버스트, 딜러: 블랙잭
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.SIX)
                        ),
                        3000
                ),
                // 플레이어0: 버스트, 딜러: 버스트
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN)
                        ),
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN)
                        ),
                        -2500
                )
        );
    }

    public static Stream<Arguments> provideForHitTest() {
        return Stream.of(
                // 17
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.SIX),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        "Q클로버, 6클로버, A클로버"
                ),
                // 블랙잭
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.KING),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        "Q클로버, K클로버"
                )
        );
    }

    void participantsAllStay(List<AbstractParticipant> participants) {
        participants.stream()
                .filter(participant -> !participant.isFinished())
                .forEach(AbstractParticipant::stay);
    }

    @BeforeEach
    void setUp() {
        List<Card> player0Cards = new ArrayList<>(
                Arrays.asList(
                        // 20
                        new Card(Suit.CLOVER, Denomination.JACK),
                        new Card(Suit.CLOVER, Denomination.KING)
                )
        );

        List<Card> player1Cards = new ArrayList<>(
                Arrays.asList(
                        // 블랙잭
                        new Card(Suit.CLOVER, Denomination.JACK),
                        new Card(Suit.CLOVER, Denomination.ACE)
                )
        );

        List<Card> player2Cards = new ArrayList<>(
                Arrays.asList(
                        // 19
                        new Card(Suit.CLOVER, Denomination.JACK),
                        new Card(Suit.CLOVER, Denomination.NINE)
                )
        );

        List<Card> player3Cards = new ArrayList<>(
                Arrays.asList(
                        // 12
                        new Card(Suit.CLOVER, Denomination.JACK),
                        new Card(Suit.CLOVER, Denomination.TWO)
                )
        );

        List<Card> dealerCards = new ArrayList<>(
                Arrays.asList(
                        // 15
                        new Card(Suit.CLOVER, Denomination.JACK),
                        new Card(Suit.CLOVER, Denomination.FIVE)
                )
        );

        player0 = new Player("플레이어", 1000);
        player1 = new Player("플레이어", 1000);
        player2 = new Player("플레이어", 1000);
        player3 = new Player("플레이어", 1000);
        dealer = new Dealer();

        player0Cards.forEach(player0::hit); // 20
        player1Cards.forEach(player1::hit); // 블랙잭
        player2Cards.forEach(player2::hit); // 19
        player3Cards.forEach(player3::hit); // 12
        dealerCards.forEach(dealer::hit);   // 15
    }

    @ParameterizedTest
    @MethodSource("provideForHitTest")
    void hitTest(List<Card> cards, String expect) {
        Dealer dealer = new Dealer();

        cards.forEach(dealer::hit);
        assertThat(dealer.getCards().getName()).isEqualTo(expect);
    }

    @Test
    void getNameTest() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @ParameterizedTest
    @MethodSource("provideForProfitTest")
    void profitTest(List<Card> player0Cards, List<Card> dealerCards, int expect) {
        player0Cards.forEach(player0::hit);
        dealerCards.forEach(dealer::hit);

        List<AbstractParticipant> players = List.of(player0, player1, player2, player3);

        participantsAllStay(players);

        assertThat(dealer.profit((List<Player>) (List<?>) players)).isEqualTo(expect);
    }

    @Test
    void stayTest() {
        Dealer dealer = new Dealer();

        dealer.stay();
        assertThat(dealer.isFinished()).isFalse();

        dealer.hit(new Card(Suit.CLOVER, Denomination.NINE));
        assertThat(dealer.isFinished()).isFalse();

        dealer.hit(new Card(Suit.CLOVER, Denomination.ACE));
        assertThat(dealer.isFinished()).isFalse();

        dealer.hit(new Card(Suit.CLOVER, Denomination.ACE));
        assertThat(dealer.isFinished()).isTrue();
    }
}
