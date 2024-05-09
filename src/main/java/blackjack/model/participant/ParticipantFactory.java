package blackjack.model.participant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantFactory {
    private static final String NAME_SEPARATOR = ",";

    public static Dealer createDealer(String name) {
        return new Dealer(name);
    }

    public static List<Player> createPlayers(String playerNames) {
        return Arrays.stream(playerNames.split(NAME_SEPARATOR))
                .map(ParticipantFactory::createPlayer)
                .collect(Collectors.toList());
    }

    private static Player createPlayer(String name) {
        return new Player(name);
    }
}
