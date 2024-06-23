package blackjack.model.state.abstracts;

import blackjack.model.card.Cards;
import blackjack.model.state.State;

public abstract class Started implements State {
    private final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return this.cards;
    }

}
