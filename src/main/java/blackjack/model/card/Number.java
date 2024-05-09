package blackjack.model.card;

import java.util.*;

public enum Number {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K",10),
    QUEEN("Q", 10),
    JACK("J", 10);

    private static final int SIZE_OR_NUMBER = 13;
    private static Map<Integer, Number> map = new HashMap<>();

    static {
        map.put(0, ACE);
        map.put(1, TWO);
        map.put(2, THREE);
        map.put(3, FOUR);
        map.put(4, FIVE);
        map.put(5, SIX);
        map.put(6, SEVEN);
        map.put(7, EIGHT);
        map.put(8, NINE);
        map.put(9, TEN);
        map.put(10, KING);
        map.put(11, QUEEN);
        map.put(12, JACK);
    }

    public static int sum (List<Number> cards) {
        return cards.stream()
                .filter(Objects::nonNull)
                .map(card -> card.value)
                .reduce(Integer::sum)
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Number pick() {
        int random = Random.create(SIZE_OR_NUMBER);
        return map.get(random);
    }

    private final String name;
    private final int value;

    Number(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
