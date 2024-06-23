package blackjack.view;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.view.View.enterLine;
import static blackjack.view.View.print;

public class ResultView {

    public static void showInitialCards(Dealer dealer, List<Player> players) {
        String playersNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "));

        print(String.format("%s와 %s에게 2장의 나누었습니다.", dealer.getName(), playersNames));

        showInitialCards(dealer);
        players.forEach(ResultView::showInitialCards);
        enterLine();
    }

    private static void showInitialCards(Participant participant) {
        String name = participant.getName();
        String cards = participant.getCards().getName();

        if (participant instanceof Dealer) {
            String firstCard = cards.split(",")[0];
            print(String.format("%s: %s", name, firstCard));
            return;
        }
        print(String.format("%s카드: %s", name, cards));
    }

    public static void showHitCards(Participant participant) {
        if (participant instanceof Dealer) {
            print(String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", participant.getName()));
        }
        print(String.format("%s카드: %s", participant.getName(), participant.getCards().getName()));
    }

    public static void showGameResult(Dealer dealer, List<Player> players) {
        showCardsAndScore(dealer, players);
        enterLine();
        showProfit(dealer, players);
    }

    private static void showCardsAndScore(Dealer dealer, List<Player> players) {
        showParticipantResult(dealer);
        players.forEach(ResultView::showParticipantResult);
    }

    private static void showParticipantResult(Participant participant) {
        if (participant instanceof Dealer) {
            print(String.format("%s 카드: %s - 결과: %d", participant.getName(), participant.getCards().getName(), participant.getCards().getScore()));
            return;
        }
        print(String.format("%s카드: %s - 결과: %d", participant.getName(), participant.getCards().getName(), participant.getCards().getScore()));
    }

    private static void showProfit(Dealer dealer, List<Player> players) {
        print("## 최종 수익");
        showProfit(dealer.getName(), dealer.profit(players));
        players.forEach(player -> showProfit(player.getName(), player.profit(dealer)));
    }

    private static void showProfit(String name, double profit) {
        print(String.format("%s: %d", name, (int) profit));
    }

    public static void showDealerDrawnCard(Dealer dealer) {
        enterLine();
        print(String.format("%s는 %d이하라 한장의 카드를 더 받았습니다.", dealer.getName(), Dealer.DRAW_SKIP_SCORE));
        enterLine();
    }
}
