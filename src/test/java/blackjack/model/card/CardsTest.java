package blackjack.model.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardsTest {

    public static Stream<Arguments> provideForGetScoreTest() {
        return Stream.of(
                Arguments.of(
                        // 에이스 없음
                        List.of(
                                new Card(Suit.CLOVER, Denomination.NINE),
                                new Card(Suit.CLOVER, Denomination.THREE)
                        ),
                        12
                ),
                Arguments.of(
                        // 9 + 에이스
                        List.of(
                                new Card(Suit.CLOVER, Denomination.NINE),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        20
                ),
                Arguments.of(
                        // 10 + 에이스
                        List.of(
                                new Card(Suit.CLOVER, Denomination.TEN),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        21
                ),
                Arguments.of(
                        // 10 + 에이스 여러장(에이스 한장 11로 판정)
                        List.of(
                                new Card(Suit.CLOVER, Denomination.SEVEN),
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.SPADE, Denomination.ACE),
                                new Card(Suit.HART, Denomination.ACE),
                                new Card(Suit.DIAMOND, Denomination.ACE)
                        ),
                        21
                ),
                Arguments.of(
                        // 10 + 에이스 여러장(에이스 모두 1로 판정)
                        List.of(
                                new Card(Suit.CLOVER, Denomination.SEVEN),
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.SPADE, Denomination.ACE),
                                new Card(Suit.HART, Denomination.ACE),
                                new Card(Suit.DIAMOND, Denomination.ACE)
                        ),
                        21
                )
        );
    }

    private Cards addingToCards(List<Card> cards) {
        Cards cardObject = new Cards();
        cards.forEach(cardObject::add);

        return cardObject;
    }

    public static Stream<Arguments> provideForBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.NINE)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.TEN),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.ACE),
                                new Card(Suit.CLOVER, Denomination.TEN),
                                new Card(Suit.CLOVER, Denomination.JACK)
                        ),
                        false
                )
        );
    }

    public static Stream<Arguments> provideForIsBustTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.TWO)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(Suit.CLOVER, Denomination.JACK),
                                new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.CLOVER, Denomination.ACE)
                        ),
                        false
                )
        );
    }

    @Test
    void addTest() {
        Cards cards = addingToCards(List.of(
                new Card(Suit.CLOVER, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.THREE)
        ));

        cards.add(new Card(Suit.CLOVER, Denomination.FOUR));

        assertThat(cards.getScore()).isEqualTo(9);
    }

    @ParameterizedTest
    @MethodSource("provideForBlackjackTest")
    void isBlackjackTest(List<Card> testCards, boolean expect) {
        Cards cards = addingToCards(testCards);

        assertThat(cards.isBlackjack()).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideForIsBustTest")
    void isBustTest(List<Card> testCards, boolean expect) {
        Cards cards = addingToCards(testCards);

        assertThat(cards.isBust()).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideForGetScoreTest")
    void getScoreTest(List<Card> testCards, int expect) {
        Cards cards = addingToCards(testCards);

        assertThat(cards.getScore()).isEqualTo(expect);
    }

    @Test
    void getNameTest() {
        Cards cards = addingToCards(List.of(
                new Card(Suit.HART, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.NINE),
                new Card(Suit.DIAMOND, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.QUEEN)
        ));

        assertThat(cards.getName()).isEqualTo("A하트, 9클로버, J다이아몬드, Q스페이드");
    }

    @Test
    void sizeTest() {
        Cards cards = addingToCards(List.of(
                new Card(Suit.HART, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.NINE),
                new Card(Suit.DIAMOND, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.QUEEN)
        ));

        assertThat(cards.size()).isEqualTo(4);
    }
}
