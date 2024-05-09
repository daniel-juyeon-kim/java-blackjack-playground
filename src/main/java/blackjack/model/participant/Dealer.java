package blackjack.model.participant;

import blackjack.model.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dealer extends AbstractParticipant {
    private static final int NUMBER_OF_STANDARD = 16;
    public Dealer(String name) {
        super(name);
    }

    public boolean isUnderStandard() {
        return Card.sum(cards) <= NUMBER_OF_STANDARD;
    }

    public String findFirstCard() {
        return cards.get(0).getName();
    }

    public double calculateProfit(List<Player> players) {
        if (this.isBust()) {
            return -1 * sumPlayerBettingAmount(players);
        }

        if (this.isBlackJack() && isContainBlackJack(players)) {
            return sumPlayerBettingAmount(players) - sumBlackJackPlayersBettingAmount(players);
        }

        if (this.isBlackJack()) {
            return sumPlayerBettingAmount(players);
        }

        if (isContainBlackJack(players)) {
            return sumPlayerBettingAmount(players) - (1.5 * sumBlackJackPlayersBettingAmount(players));
        }

        if (this.cardsSum() == findMaxCardSum(players)) {
            return sumPlayerBettingAmount(players) - sumWinnersProfit(players, findMaxCardSum(players));
        }

        if (this.cardsSum() < findMaxCardSum(players)) {
            return sumPlayerBettingAmount(players) - 2 * sumWinnersProfit(players, findMaxCardSum(players));
        }

        return sumPlayerBettingAmount(players);
    }

    private int findMaxCardSum(List<Player> players) {
        List<Integer> notBustPlayersCardSums = filterBustPlayer(players)
                .map(AbstractParticipant::cardsSum)
                .collect(Collectors.toList());

        return Collections.max(notBustPlayersCardSums);
    }

    private Stream<Player> filterBustPlayer(List<Player> players) {
        return players.stream().filter(player -> !player.isBust());
    }

    private int sumWinnersProfit(List<Player> players, int maxCardSum) {
        return filterWinner(players, maxCardSum)
                .map(Player::getBettingAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private Stream<Player> filterWinner(List<Player> players, int maxCardSum) {
        return players.stream()
                .filter(player -> player.cardsSum() == maxCardSum);
    }

    private int sumBlackJackPlayersBettingAmount(List<Player> players) {
        return filterBlackJack(players)
                .map(Player::getBettingAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private boolean isContainBlackJack(List<Player> players) {
        return filterBlackJack(players).count() > 0;
    }

    private Stream<Player> filterBlackJack(List<Player> players) {
        return players.stream()
                .filter(Player::isBlackJack);
    }

    private int sumPlayerBettingAmount(List<Player> players) {
        return players.stream()
                .map(Player::getBettingAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
