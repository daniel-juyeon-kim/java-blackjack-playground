package blackjack.view;

import blackjack.model.participant.Player;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static blackjack.view.View.enterLine;
import static blackjack.view.View.print;

public class InputView {

    private static final String SPLITTER = ",";
    private static final Scanner in = new Scanner(System.in);

    public static List<Player> inputNames() {
        print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> names = splitBySplitter(in.nextLine());

        enterLine();

        return names.stream()
                .map(name -> new Player(name, betting(name)))
                .collect(Collectors.toList());
    }

    private static List<String> splitBySplitter(String string) {
        return List.of(string.split(SPLITTER));
    }

    private static int betting(String name) {
        try{
            print(String.format("%s의 배팅 금액은?", name));
            int bettingAmount = Integer.parseInt(in.nextLine());
            enterLine();
            return bettingAmount;
        } catch (NumberFormatException e) {
            print("숫자가 아닙니다. 숫자를 입력해 주세요");
            return betting(name);
        }
    }

    public static boolean choiceHit(Player player) {
        print(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
        String choice = in.nextLine();

        if (choice.equals("y")) {
            return true;
        } else if (choice.equals("n")) {
            return false;
        }
        print("y 또는 n을 입력해 주세요.");
        return choiceHit(player);
    }

}
