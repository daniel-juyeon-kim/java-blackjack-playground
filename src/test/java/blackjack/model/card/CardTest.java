package blackjack.model.card;

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

public class CardTest {
    public static Stream<Arguments> provideValueForGetName() {
        return Stream.of(
                Arguments.of(
                        new Card(Type.HART, Number.ACE).getName(),
                        "A하트"
                ),
                Arguments.of(
                        new Card(Type.HART, Number.KING).getName(),
                        "K하트"
                ),
                Arguments.of(
                        new Card(Type.HART, Number.QUEEN).getName(),
                        "Q하트"
                ),
                Arguments.of(
                        new Card(Type.HART, Number.JACK).getName(),
                        "J하트"
                ),
                Arguments.of(
                        new Card(Type.HART, Number.TEN).getName(),
                        "10하트"
                )
        );
    }

    public static Stream<Arguments> provideValuesForIsBust() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE),
                                new Card(Type.SPADE, Number.ACE),
                                new Card(Type.HART, Number.KING),
                                new Card(Type.HART, Number.NINE)
                        ),
                        false
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE),
                                new Card(Type.SPADE, Number.ACE),
                                new Card(Type.HART, Number.KING),
                                new Card(Type.HART, Number.TEN)
                        ),
                        true
                )
        );
    }

    public static Stream<Arguments> provideValuesForSum() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE),
                                new Card(Type.SPADE, Number.ACE),
                                new Card(Type.HART, Number.KING),
                                new Card(Type.HART, Number.TEN)
                        ),
                        22
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE),
                                new Card(Type.SPADE, Number.ACE),
                                new Card(Type.HART, Number.KING),
                                new Card(Type.HART, Number.NINE)
                        ),
                        21
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.DIAMOND, Number.QUEEN),
                                new Card(Type.HART, Number.KING),
                                new Card(Type.DIAMOND, Number.ACE)
                        ),
                        21
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.JACK),
                                new Card(Type.CLOVER, Number.ACE)
                        ),
                        21
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.CLOVER, Number.ACE),
                                new Card(Type.HART, Number.JACK)
                        ),
                        21
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.QUEEN),
                                new Card(Type.HART, Number.KING)
                        ),
                        20
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.THREE),
                                new Card(Type.HART, Number.TWO)
                        ),
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideValueForGetName")
    void getName(String cardName, String expect) {
        assertThat(cardName).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForSum")
    void sum(List<Card> cards, int expect) {
        assertThat(Card.sum(cards)).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForIsBust")
    void isBust(List<Card> cards, boolean expect) {
         assertThat(Card.isBust(cards)).isEqualTo(expect);
    }
}
