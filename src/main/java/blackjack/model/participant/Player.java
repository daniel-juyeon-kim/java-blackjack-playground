package blackjack.model.participant;

import blackjack.model.card.Card;

public class Player extends AbstractParticipant{
    private final String name;
    private final int bettingAmount;

    public Player(String name, int bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    @Override
    public void hit(Card card) {
        try {
            this.state = state.draw(card);
        }catch (IllegalCallerException e) {
            return;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public double profit(Dealer dealer) {
        if (isTie(dealer)) {
            return 0;
        } else if (this.isBust() || this.isWin(dealer)) {
            return state.profit(bettingAmount);
        }
        return -1 * state.profit(bettingAmount);
    }

    private boolean isTie(Dealer dealer) {
        return (this.isBlackjack() && dealer.isBlackjack()) ||
                isNotBustTie(dealer);
    }

    private boolean isNotBustTie(Dealer dealer) {
        return !this.isBust() && (this.getScore() == dealer.getScore());
    }

    private boolean isWin(Dealer dealer) {
        return this.isBlackjack() || dealer.isBust() || dealer.getScore() < this.getScore();
    }

}
