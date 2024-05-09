package blackjack.model;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.card.CardFactory;
import blackjack.view.InputView;
import blackjack.view.ResultView;

import java.util.List;

public class Game {

    public static void play(Dealer dealer, List<Player> players) {
        drawInitialCards(dealer, players);
        drawCard(dealer, players);
    }

    private static void drawCard(Dealer dealer, List<Player> players) {
        while (wantDrawCard(players)) {
            players.stream()
                    .filter(Player::wantsDraw)
                    .forEach(Game::drawCard);
        }
        drawOneMore(dealer);
    }

    private static void drawInitialCards(Dealer dealer, List<Player> players) {
        dealer.draw(CardFactory.create());
        dealer.draw(CardFactory.create());

        players.forEach(player -> player.draw(CardFactory.create()));
        players.forEach(player -> player.draw(CardFactory.create()));

        ResultView.showDrawCards(dealer, players);
    }

    private static void drawOneMore(Dealer dealer) {
        if (dealer.isUnderStandard()) {
            ResultView.printUnderStandard(dealer);
            dealer.draw(CardFactory.create());
        }
    }

    private static boolean wantDrawCard(List<Player> players) {
        return players.stream()
                .filter(Player::wantsDraw)
                .count() != 0;
    }

    private static void drawCard(Player player) {
        if (InputView.choiceDraw(player)) {
            player.draw(CardFactory.create());
            ResultView.showDrawCards(player, player.cardToString());
            return;
        }

        player.doesNotDraw();
    }

}
