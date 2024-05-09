package blackjack.model.card;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    SPADE("스페이드"),
    HART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private static final int SIZE_OF_TYPES = 4;
    private static Map<Integer, Type> map = new HashMap<>();

    static {
        map.put(0, SPADE);
        map.put(1, HART);
        map.put(2, CLOVER);
        map.put(3, DIAMOND);
    }

    public static Type pick() {
        int random = Random.create(SIZE_OF_TYPES);
        return map.get(random);
    }

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
