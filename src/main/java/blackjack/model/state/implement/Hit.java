package blackjack.model.state.implement;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.state.State;
import blackjack.model.state.abstracts.Running;

public class Hit extends Running {
    private static final String ERROR_STATE_IS_RUNNING = "드로우가 끝내지 않은 상태입니다.";
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards().add(card);

        if(cards().isBlackjack()) {
            return new Blackjack(cards());
        } else if(cards().isBust()) {
            return new Bust(cards());
        }
        return new Hit(cards());
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }

    @Override
    public double profit(double bettingAmount) {
        throw new IllegalStateException(ERROR_STATE_IS_RUNNING);
    }
}