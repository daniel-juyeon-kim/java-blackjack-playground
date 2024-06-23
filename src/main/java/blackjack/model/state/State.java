package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public interface State {
    State draw(Card card) throws IllegalCallerException;
    State stay() throws IllegalCallerException;
    Cards cards();
    boolean isFinished();
    double profit(double bettingAmount) throws IllegalStateException;
}
