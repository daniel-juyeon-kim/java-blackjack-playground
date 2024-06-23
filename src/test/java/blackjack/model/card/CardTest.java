package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    private final Card cloverAce = new Card(Suit.CLOVER, Denomination.ACE);
    private final Card cloverTwo = new Card(Suit.CLOVER, Denomination.TWO);

    @Test
    void isAceTest() {
        assertThat(cloverAce.isAce()).isTrue();
        assertThat(cloverTwo.isAce()).isFalse();
    }

    @Test
    void getScoreTest() {
        assertThat(cloverAce.getScore()).isEqualTo(1);
        assertThat(cloverTwo.getScore()).isEqualTo(2);
    }

    @Test
    void getNameTest() {
        assertThat(new Card(Suit.CLOVER, Denomination.ACE).getName()).isEqualTo("A클로버");
        assertThat(new Card(Suit.CLOVER, Denomination.TWO).getName()).isEqualTo("2클로버");
        assertThat(new Card(Suit.CLOVER, Denomination.JACK).getName()).isEqualTo("J클로버");
        assertThat(new Card(Suit.CLOVER, Denomination.KING).getName()).isEqualTo("K클로버");
        assertThat(new Card(Suit.CLOVER, Denomination.QUEEN).getName()).isEqualTo("Q클로버");
    }
}
