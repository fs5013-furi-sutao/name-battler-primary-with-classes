package src.jp.jboocamp.chara;

import src.jp.jboocamp.setting.Config;
import src.jp.jboocamp.status.Damage;
import src.jp.jboocamp.status.Status;
import src.jp.jboocamp.util.InputReciever;

public class Player {
    private static int maxPlayerNo;
    private int playerNo;
    private Status status;
    private Damage damage;

    static {
        maxPlayerNo = Config.Values.START_OF_PLAYER_NO;
    }

    public Player(Players players) {
        generatePlayerNo();
        String name = recieveUserInputtedName(players);
        this.status = new Status(name);
        this.damage = new Damage();
    }

    private String recieveUserInputtedName(Players players) {
        showRequirePlayerName();
        String inputtedName = InputReciever.recieveInputtedStr();
        inputtedName = validateUserInputtedName(players, inputtedName);
        return inputtedName;
    }

    private String validateUserInputtedName(Players players, String name) {
        if (!isLengthInRange(name)) {
            return recieveUserInputtedName(players);
        }

        for (Player player : players.toList()) {

            if (player.isSameName(name)) {
                return recieveUserInputtedName(players);
            }
        }
        return name;
    }

    private boolean isLengthInRange(String name) {
        return false;
    }

    private boolean isSameName(String name) {
        return false;
    }

    private void generatePlayerNo() {
        this.playerNo = maxPlayerNo;
        incrementPlayerNo();
    }

    private void incrementPlayerNo() {
        maxPlayerNo++;

    }

    private void showRequirePlayerName() {
    }

    public static Player generatePlayerWithNonDupulicatedNames(
            Players players) {
        return new Player(players);
    }
}
