package blackjack.model.state.abstracts;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.state.State;

public abstract class Finished extends Started {

    private static final String ERROR_ALREADY_FINISHED = "이미 종료되었습니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalCallerException(ERROR_ALREADY_FINISHED);
    }

    @Override
    public State stay() {
        throw new IllegalCallerException(ERROR_ALREADY_FINISHED);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double bettingAmount) {
        return earningRate() * bettingAmount;
    }

    abstract protected double earningRate();

}
