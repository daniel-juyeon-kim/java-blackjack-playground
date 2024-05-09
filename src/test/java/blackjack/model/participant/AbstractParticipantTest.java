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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AbstractParticipantTest {
    public static Stream<Arguments> provideValuesForInitialDraw() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE)
                        ),
                        true
                ),
                Arguments.of(
                        Arrays.asList(
                                new Card(Type.HART, Number.ACE),
                                new Card(Type.HART, Number.JACK)
                        ),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideValuesForInitialDraw")
    void initialDraw(List<Card> cards, boolean isThrow) {
        Player p = new Player("dan");

        if (isThrow) {
            assertThatThrownBy(() -> p.initialDraw(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("초기 카드 수령 에러 발생");

            return;
        }

        assertDoesNotThrow(() -> {
            p.initialDraw(cards);
        });
    }

}
