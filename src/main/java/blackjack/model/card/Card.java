package blackjack.model.card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private static final int NUMBER_BLACKJACK = 21;
    private static final int NUMBER_MAX_ACE = 11;
    private static final int ALL_CARDS_IS_ACE = 0;
    private static final int ONE_ACE_CARD = 1;

    public static boolean isBust(List<Card> cards) {
        return NUMBER_BLACKJACK < acesCount(cards) + sumNonAce(cards);
    }

    public static int sum (List<Card> cards) {
        int nonAcesSum = sumNonAce(cards);

        if (isBust(cards)) {
            return nonAcesSum + acesCount(cards);
        }

        return nonAcesSum + sumAces(cards, nonAcesSum);
    }

    private static int acesCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private static int sumNonAce(List<Card> cards) {
        return filterNonAces(cards)
                .map(card -> card.number.getValue())
                .reduce(Integer::sum)
                .orElse(ALL_CARDS_IS_ACE);
    }

    private static Stream<Card> filterNonAces(List<Card> cards) {
        return cards.stream().filter(Card::isNotAce);
    }

    private static int sumAces(List<Card> cards, int nonAcesSum) {
        if (!filterAce(cards).findAny().isPresent()) {
            return 0;
        }
        else if (NUMBER_BLACKJACK - nonAcesSum < NUMBER_MAX_ACE) {
            return acesCount(cards);
        }
        return acesCount(cards) - ONE_ACE_CARD + NUMBER_MAX_ACE;
    }

    private static Stream<Card> filterAce(List<Card> cards) {
        return cards.stream().filter(Card::isAce);
    }

    private boolean isNotAce() {
        return !isAce();
    }

    private boolean isAce() {
        return this.number == Number.ACE;
    }

    private final Type type;
    private final Number number;

    public Card(Type type, Number number) {
        this.type = type;
        this.number = number;
    }

    public String getName() {
        return number.getName() + type.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}
