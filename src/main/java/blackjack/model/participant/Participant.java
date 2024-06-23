package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public interface Participant {
    void hit(Card card);
    void stay();
    String getName();
    Cards getCards();
    boolean isFinished();
}
