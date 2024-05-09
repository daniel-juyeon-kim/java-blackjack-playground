package blackjack.model.participant;

import java.util.List;
import java.util.stream.Stream;

public class Player extends AbstractParticipant {
    private boolean wantDraw = true;
    private int bettingAmount;

    public Player(String name) {
        super(name);
    }

    public void doesNotDraw () {
        this.wantDraw = !wantDraw;
    }

    public boolean wantsDraw() {
        return wantDraw;
    }

    public void bet(int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "wantDraw=" + wantDraw +
                ", bettingAmount=" + bettingAmount +
                ", cards=" + cards +
                '}';
    }

    public double calculateProfit(Dealer dealer, List<Player> players) {
//      딜러가 버스트면 배팅금액을 돌려받음
        if (dealer.isBust()) {
            return bettingAmount;
        }

//        내가 버스트면 배팅금액을 잃음
        if (this.isBust()) {
            return -1 * bettingAmount;
        }

//        딜러와 플레이어 둘다 블랙잭이면 배팅한 금액을 돌려받음
        if (this.isBlackJack() && dealer.isBlackJack()) {
            return 0;
        }

//        블랙잭은 금액의 1.5배를 딜러에게 받음
        if (this.isBlackJack()) {
            return 1.5 * bettingAmount;
        }

//        카드의 밸류가 같고 가장 높으면 0 리턴
        if (this.isNotBustHighestPlayer(players) && this.cardsSum() == dealer.cardsSum()) {
            return 0;
        }

//        나머지는 가장 높은 사람이 배팅금액을 가지고 감
//        아닌거는 잃음
        if (this.isNotBustHighestPlayer(players)) {
            return bettingAmount;
        }

        return -1 * bettingAmount;
    }

    private boolean isNotBustHighestPlayer(List<Player> players) {
        return countNotBustHigherPlayerThanMe(players) == 0;
    }

    private int countNotBustHigherPlayerThanMe(List<Player> players) {
        return (int) filterNotBustPlayers(players)
                .filter(player -> player.cardsSum() > this.cardsSum())
                .count();
    }

    private Stream<Player> filterNotBustPlayers(List<Player> players) {
        return players.stream().filter(player -> !player.isBust());
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
