package blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DenominationTest {
    @Test
    void findTest() {
        assertThat(Denomination.find(0)).isEqualTo(Denomination.ACE);
        assertThat(Denomination.find(12)).isEqualTo(Denomination.QUEEN);
    }

    @Test
    void isAceTest() {
        assertThat(Denomination.ACE.isAce()).isTrue();
        assertThat(Denomination.TWO.isAce()).isFalse();
    }

    @Test
    void isMajorTest() {
        assertThat(Denomination.ACE.isMajor()).isFalse();
        assertThat(Denomination.KING.isMajor()).isTrue();
        assertThat(Denomination.QUEEN.isMajor()).isTrue();
        assertThat(Denomination.JACK.isMajor()).isTrue();
    }

    @Test
    void getScoreTest() {
        assertThat(Denomination.ACE.getScore()).isEqualTo(1);
        assertThat(Denomination.KING.getScore()).isEqualTo(10);
    }
}
