package blackjack.model.participant;

import blackjack.model.card.Cards;
import blackjack.model.state.State;
import blackjack.model.state.implement.Hit;

public abstract class AbstractParticipant implements Participant{
    protected State state = new Hit(new Cards());

    @Override
    public void stay() {
        try {
            this.state = state.stay();
        } catch (IllegalCallerException e) {
            return;
        }
    }

    @Override
    public Cards getCards() {
        return state.cards();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    protected boolean isBust() {
        return state.cards().isBust();
    }

    protected boolean isBlackjack() {
        return state.cards().isBlackjack();
    }

    protected int getScore() {
        return state.cards().getScore();
    };
}
