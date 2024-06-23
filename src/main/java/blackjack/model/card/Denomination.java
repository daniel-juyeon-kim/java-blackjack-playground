package blackjack.model.card;

import java.util.Arrays;

public enum Denomination {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    KING(10),
    QUEEN(10);

    private final int score;
    private Denomination(int score) {
        this.score = score;
    }
    public static Denomination find(int index) {
        return Arrays.asList(Denomination.values()).get(index);
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isMajor() {
        return this == Denomination.KING ||
                this == Denomination.QUEEN ||
                this == Denomination.JACK;
    }

}
