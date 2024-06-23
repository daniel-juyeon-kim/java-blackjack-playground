package blackjack.model.card;

public class CardFactory {

    public static Card create() {
        int randomSuitIndex = Random.createZeroToMax(Suit.values().length);
        int randomDenominationIndex = Random.createZeroToMax(Denomination.values().length);

        return new Card(Suit.find(randomSuitIndex), Denomination.find(randomDenominationIndex));
    }

}
