package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Number;
import blackjack.model.card.Type;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DealerTest {

    public static void drawCards(AbstractParticipant participant, List<Card> cards) {
        cards.forEach(participant::draw);
    }

    public static Stream<Arguments> provideValuesForIsEqualsOrUnderStandard() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                            new Card(Type.CLOVER, Number.SIX),
                            new Card(Type.CLOVER, Number.JACK)
                        ),
                        true
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.CLOVER, Number.SEVEN),
                                new Card(Type.CLOVER, Number.JACK)
                        ),
                        false
                )
        );
    }

    public static Stream<Arguments> provideValuesForCalculateProfit() {
        return Stream.of(
//                Dealer bust test
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.TWO)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.KING)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.KING)
                                )
                        ),
                        -3000
                ),
//                Dealer and player blackjack test
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.CLOVER, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.CLOVER, Number.EIGHT)
                                ),
                                Arrays.asList(
                                        new Card(Type.DIAMOND, Number.QUEEN),
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.DIAMOND, Number.ACE)
                                )
                        ),
                        1000
                ),
//                Dealer blackjack test
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.TWO)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.NINE),
                                        new Card(Type.HART, Number.KING)
                                )
                        ),
                        3000
                ),
//               player is blackjack
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.SPADE, Number.JACK)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.JACK)
                                )
                        ),
                        -1500
                ),
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        1500
                ),
//                one player win other player lose
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.QUEEN)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.SEVEN),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        1000
                ),
//                dealer win
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.THREE)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.SEVEN),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        3000
                ),
//                dealer and player draw
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.SEVEN),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        2000
                ),
//                dealer and player bust
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.TWO),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.QUEEN)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.KING),
                                        new Card(Type.SPADE, Number.NINE),
                                        new Card(Type.SPADE, Number.QUEEN)
                                )
                        ),
                        1000
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideValuesForIsEqualsOrUnderStandard")
    void isEqualsOrUnderStandard(List<Card> cards, boolean expect) {
        Dealer dealer = ParticipantFactory.createDealer("딜러");
        dealer.initialDraw(cards);

        assertThat(dealer.isUnderStandard()).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForCalculateProfit")
    void calculateProfit(List<List<Card>> deck, double expectProfit) {
        Dealer dealer = ParticipantFactory.createDealer("딜러");
        List<Player> players = ParticipantFactory.createPlayers("dan,de");

        Player dan = players.get(0);
        Player de = players.get(1);

        dan.bet(1000);
        de.bet(2000);

        drawCards(dealer, deck.get(0));
        drawCards(dan, deck.get(1));
        drawCards(de, deck.get(2));

        double profit = dealer.calculateProfit(players);

        assertThat(profit).isEqualTo(expectProfit);
    }


}
