package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Number;
import blackjack.model.card.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.model.participant.DealerTest.drawCards;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

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
                        1000
                ),
//                Dealer bust player bust test
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.TWO)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.KING)
                                )
                        ),
                        1000
                ),
//                Player bust test
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.CLOVER, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.JACK),
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.TWO)
                                ),
                                Arrays.asList(
                                        new Card(Type.DIAMOND, Number.QUEEN),
                                        new Card(Type.HART, Number.KING)
                                )
                        ),
                        -1000
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
                                        new Card(Type.CLOVER, Number.KING)
                                ),
                                Arrays.asList(
                                        new Card(Type.DIAMOND, Number.QUEEN),
                                        new Card(Type.HART, Number.KING)
                                )
                        ),
                        0
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
                        -1000
                ),
//               player blackjack
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
                        1500
                ),
//               I'm highest score
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.KING),
                                        new Card(Type.HART, Number.QUEEN)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        1000
                ),
//               I've not highest score
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.ACE)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.THREE),
                                        new Card(Type.HART, Number.TWO)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.ACE),
                                        new Card(Type.SPADE, Number.TWO)
                                )
                        ),
                        -1000
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
                        0
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
                ),
//                dealer win
                Arguments.of(
                        Arrays.asList(
                                Arrays.asList(
                                        new Card(Type.HART, Number.TEN),
                                        new Card(Type.HART, Number.JACK)
                                ),
                                Arrays.asList(
                                        new Card(Type.SPADE, Number.FIVE),
                                        new Card(Type.SPADE, Number.NINE),
                                        new Card(Type.SPADE, Number.FOUR)
                                ),
                                Arrays.asList(
                                        new Card(Type.HART, Number.ACE),
                                        new Card(Type.HART, Number.SIX)
                                )
                        ),
                        1000
                )
        );
    }

    @Test
    void wantDraw() {
        Player player = new Player("dan");

        assertThat(player.wantsDraw()).isEqualTo(true);

        player.doesNotDraw();
        assertThat(player.wantsDraw()).isEqualTo(false);
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

        double profit = dan.calculateProfit(dealer, players);

        assertThat(profit).isEqualTo(expectProfit);
    }
}
