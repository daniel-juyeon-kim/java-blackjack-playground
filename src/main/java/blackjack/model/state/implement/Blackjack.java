package blackjack.model.state.implement;

import blackjack.model.card.Cards;
import blackjack.model.state.State;
import blackjack.model.state.abstracts.Finished;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return 1.5;
    }
}
