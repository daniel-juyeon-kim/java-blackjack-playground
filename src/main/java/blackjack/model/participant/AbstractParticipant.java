package blackjack.model.participant;

import blackjack.model.card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractParticipant implements Participant{
    private static final String ERROR_INCORRECT_CARD_COUNT = "초기 카드 수령 에러 발생";
    private static final int BLACK_JACK = 21;
    private static final int INITIAL_CARDS_COUNT = 2;
    protected List<Card> cards = new LinkedList<>();
    private final String name;

    public AbstractParticipant(String name) {
        this.name = name;
    }

    @Override
    public void draw(Card card) {
        this.cards.add(card);
    }

    @Override
    public void initialDraw(List<Card> cards) {
        if (getCardsCount(cards) == INITIAL_CARDS_COUNT) {
            this.cards.addAll(cards);
            return;
        }
        throw new IllegalArgumentException(ERROR_INCORRECT_CARD_COUNT);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String cardToString() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList())
                .toString()
                .replace("[", "")
                .replace("]", "");
    }

    @Override
    public int cardsSum() {
        return Card.sum(cards);
    }

    public boolean isBust() {
        return BLACK_JACK < cardsSum();
    }

    protected boolean isBlackJack() {
        return BLACK_JACK == cardsSum();
    }

    private static int getCardsCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(Objects::nonNull)
                .count();
    }
}
