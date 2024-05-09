package blackjack.model.card;

import java.util.LinkedList;
import java.util.List;

public class CardFactory {
    private static final List<Card> createdCards = new LinkedList<>();

    public static Card create() {
        Card card = new Card(Type.pick(), Number.pick());

        while (isDuplicate(card)) {
            card = new Card(Type.pick(), Number.pick());
        }

        createdCards.add(card);
        return card;
    }

    private static boolean isDuplicate(Card card) {
        return createdCards.contains(card);
    }
}
