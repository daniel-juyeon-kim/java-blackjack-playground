package blackjack.model.card;

import java.util.Arrays;

public enum Suit {
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    HART("하트");

    private final String name;
    private Suit(String name) {
        this.name = name;
    }

    public static Suit find(int index) {
        return Arrays.asList(Suit.values()).get(index);
    }

    public String getName() {
        return name;
    }

}
