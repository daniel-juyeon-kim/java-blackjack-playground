package blackjack.model.card;

public class Random {
    public static int createZeroToMax(int max) {
        return (int) (Math.random() * max);
    }
}
