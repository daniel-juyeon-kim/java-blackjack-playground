package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

import java.util.List;

public class Dealer extends AbstractParticipant{
    private static final String NAME = "딜러";
    public static final int DRAW_SKIP_SCORE = 16;

    @Override
    public void hit(Card card) {
        Cards cards = state.cards();

        if (isCanDrawScore(cards.getScore())) {
            draw(card);
            return;
        }
        super.stay();
    }

    @Override
    public void stay() {
        if (state.cards().size() == 3) {
            super.stay();
        }
    }

    private boolean isCanDrawScore(int score) {
        return score <= DRAW_SKIP_SCORE;
    }

    private void draw(Card card) {
        try {
            state = state.draw(card);
            stay();
        } catch (IllegalCallerException e) {
            return;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public double profit(List<Player> players) {
        double playersProfit = players.stream()
                .map(player -> player.profit(this))
                .mapToDouble(Double::doubleValue)
                .sum();

        if (playersProfit == 0) {
            return 0;
        }
        return playersProfit * -1;
    }

}
