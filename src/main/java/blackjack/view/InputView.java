package blackjack.view;

import blackjack.model.participant.Player;
import blackjack.model.participant.ParticipantFactory;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String MESSAGE_INPUT_AMOUNT = "의 배팅 금액은?";
    private static final String MESSAGE_INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MESSAGE_DRAW_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ERROR_NOT_POSITIVE_NUMBER = "배팅 금액은 양수만 가능합니다.";
    private static final String ERROR_NOT_INTEGER = "정수여야 합니다.";
    private static final String ERROR_Y_OR_N = "y, n이 아닙니다.";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final Scanner in = new Scanner(System.in);

    public static boolean choiceDraw(Player player) {
        String choice = inputChoice(player);

        if (choice.equals(YES)) {
            return true;
        } else if (choice.equals(NO)) {
            return false;
        }

        showMessage(ERROR_Y_OR_N);
        return choiceDraw(player);
    }

    private static String inputChoice(Player player) {
        if (player.isBust()) {
            return NO;
        }
        showMessage(player.getName() + MESSAGE_DRAW_CARD);
        String choice = in.nextLine();
        return choice;
    }

    private static void showMessage(String message) {
        System.out.println(message);
    }

    public static List<Player> input() {
        List<Player> players = inputNames();
        players.forEach(InputView::inputBet);
        return players;
    }

    private static List<Player> inputNames() {
        showMessage(MESSAGE_INPUT_PLAYER_NAMES);
        return ParticipantFactory.createPlayers(in.nextLine());
    }

    private static void inputBet(Player player) {
        try {
            showMessage("");
            showMessage(player.getName() + MESSAGE_INPUT_AMOUNT);
            inputAmount(player);
        } catch (NumberFormatException e) {
            showMessage(ERROR_NOT_INTEGER);
            inputBet(player);
        }
    }

    private static void inputAmount(Player player) {
        int amount = Integer.parseInt(in.nextLine());

        if (isPositiveNumber(amount)) {
            player.bet(amount);
            return;
        }

        throw new IllegalArgumentException(ERROR_NOT_POSITIVE_NUMBER);
    }

    private static boolean isPositiveNumber(int amount) {
        return 0 < amount;
    }

}
