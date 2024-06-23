package blackjack.model.card;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination value) {
        this.suit = suit;
        this.denomination = value;
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getName() {
        if (isMajorOrAce()) {
            char firstCharacter = denomination.name().charAt(0);
            return firstCharacter + suit.getName();
        }
        return denomination.getScore() + suit.getName();
    }

    private boolean isMajorOrAce() {
        return denomination.isAce() || denomination.isMajor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
