package blackjack.model.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACKJACK = 21;
    private static final int MAX_ACE_SCORE = 11;

    private final List<Card> cards = new LinkedList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return this.getScore() == BLACKJACK;
    }

    public boolean isBust() {
        return BLACKJACK < this.getScore();
    }

    public int getScore() {
        List<Card> aces = this.filterAce();

        if (isExistAce(aces)) {
            return getScoreWithAce(aces);
        }
        return sumNotAceScores();
    }

    private int getScoreWithAce(List<Card> aces) {
        if (isSingular(aces)) {
            return calculateScoreWithSingularAce(aces);
        }
        return calculateScoreWithPluralAces(aces);
    }

    private int calculateScoreWithPluralAces(List<Card> aces) {
        int sumMaxAceScore = this.sumNotAceScores() + aces.size() - 1 + MAX_ACE_SCORE;
        int sumMinAceScore = this.sumNotAceScores() + aces.size();

        if (isUnderBlackjack(sumMaxAceScore)) {
            return sumMaxAceScore;
        }
        return sumMinAceScore;
    }

    private int calculateScoreWithSingularAce(List<Card> aces) {
        int sumMaxAceScore = this.sumNotAceScores() + MAX_ACE_SCORE;
        int sumMinAceScore = this.sumNotAceScores() + aces.size();

        if (isUnderBlackjack(sumMaxAceScore)) {
            return sumMaxAceScore;
        }
        return sumMinAceScore;
    }

    private boolean isUnderBlackjack(int score) {
        return score <= BLACKJACK;
    }

    private static boolean isSingular(List<Card> aces) {
        return aces.size() == 1;
    }

    private boolean isExistAce(List<Card> aces) {
        return !aces.isEmpty();
    }

    private int sumNotAceScores() {
        return this.cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    private List<Card> filterAce() {
        return this.cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
    }

    public String getName() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public int size() {
        return cards.size();
    }
}
