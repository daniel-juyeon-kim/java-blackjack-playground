package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardFactory;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.view.InputView;
import blackjack.view.ResultView;

import java.util.*;

public class Game {

    private final Set<Card> drawnCards = new HashSet<>();

    public void play() {
        List<Player> players = InputView.inputNames();
        Dealer dealer = new Dealer();

        initialDraw(dealer, players);
        hit(dealer, players);

        ResultView.showGameResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        initialDraw(dealer);
        initialDraw(players);

        ResultView.showInitialCards(dealer, players);
    }

    private void initialDraw(List<Player> participants) {
        participants.forEach(this::initialDraw);
    }

    private void initialDraw(Participant participant) {
        participant.hit(drawCard());
        participant.hit(drawCard());
    }

    private void hit(Dealer dealer, List<Player> players) {
        hit(players);
        hit(dealer);
    }

    private void hit(List<Player> players) {
        players.stream()
                .filter(player -> !player.isFinished())
                .forEach(this::hit);
    }

    private void hit(Player player) {
        if (player.isFinished()) {
            return;
        } else if (isHit(player)) {
            player.hit(drawCard());
            ResultView.showHitCards(player);
            hit(player);
            return;
        }
        player.stay();
    }

    private boolean isHit(Player player) {
        return InputView.choiceHit(player);
    }

    private void hit(Dealer dealer) {
        if (dealer.isFinished()) {
            return;
        }
        dealer.hit(drawCard());
        ResultView.showDealerDrawnCard(dealer);
    }

    private Card drawCard() {
        Card card = CardFactory.create();

        if (drawnCards.contains(card)) {
            return drawCard();
        }
        drawnCards.add(card);

        return card;
    }
}
