package blackjack.model.card;

public class Random {
    public static int create(int end) {
        return (int) (Math.random() * end);
    }
}
