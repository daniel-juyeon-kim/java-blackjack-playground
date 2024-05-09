package blackjack.view;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static void showResults(Dealer dealer, List<Player> players) {
        print("");
        showResult(dealer);
        players.forEach(ResultView::showResult);
        showProfit(dealer, players);
    }

    private static void showProfit(Dealer dealer, List<Player> players) {
        print("");
        print("##최종 수익");
        print(String.format("%s: %d", dealer.getName(), (int) dealer.calculateProfit(players)));
        players.forEach(player -> {
            print(String.format("%s: %d", player.getName(), (int) player.calculateProfit(dealer, players)));
        });
    }

    private static void showResult(Participant participant) {
        print(String.format("%s 카드: %s - 결과: %d", participant.getName(), participant.cardToString(), participant.cardsSum()));
    }

    public static void showDrawCards(Dealer dealer, List<Player> players) {
        print("");
        print(String.format("%s와 %s에게 2장 나누었습니다.", dealer.getName(), mappingPlayerNames(players)));
        showDrawCards(dealer, dealer.findFirstCard());
        players.forEach(player -> {
            showDrawCards(player, player.cardToString());
        });
        print("");
    }

    private static String mappingPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList())
                .toString()
                .replace("[", "")
                .replace("]","");
    }

    public static void showDrawCards(Participant participant, String cards) {
        print(String.format("%s 카드: %s", participant.getName(), cards));
    }

    private static void print(String message) {
        System.out.println(message);
    }

    public static void printUnderStandard(Dealer dealer) {
        print(String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName()));
    }
}
