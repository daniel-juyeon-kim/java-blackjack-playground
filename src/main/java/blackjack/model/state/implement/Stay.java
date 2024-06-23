package blackjack.model.state.implement;

import blackjack.model.card.Cards;
import blackjack.model.state.abstracts.Finished;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return 1;
    }
}
