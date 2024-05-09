package blackjack.model.participant;

import blackjack.model.card.Card;

import java.util.List;

public interface Participant {
    void draw(Card card);
    void initialDraw(List<Card> cards);
    String getName();
    String cardToString();
    int cardsSum();
}
