package blackjack.controller;

import blackjack.model.Game;

public class BlackjackController {
    public static void run() {
        new Game().play();
    }
}
