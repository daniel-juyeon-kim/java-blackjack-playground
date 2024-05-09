package blackjack.controller;

import blackjack.model.Game;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.ParticipantFactory;
import blackjack.model.participant.Player;
import blackjack.view.InputView;
import blackjack.view.ResultView;

import java.util.List;

public class BlackJackController {
    public static void run() {
        List<Player> players = InputView.input();
        Dealer dealer = ParticipantFactory.createDealer("딜러");
        Game.play(dealer, players);
        ResultView.showResults(dealer, players);
    }
}
