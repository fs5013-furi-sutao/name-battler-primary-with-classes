package jp.jboocamp.namebattler.chara;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.status.Damage;
import jp.jboocamp.namebattler.status.Status;
import jp.jboocamp.namebattler.util.Console;
import jp.jboocamp.namebattler.util.InputReciever;

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
            showRequireInputInDefinedLength();
            return recieveUserInputtedName(players);
        }

        for (Player player : players.toList()) {

            if (player.isSameName(name)) {
                showRequireInputNonDuplicatedNames();
                return recieveUserInputtedName(players);
            }
        }
        return name;
    }

    private boolean isLengthInRange(String name) {
        int min = Config.Values.MIN_LENGTH_PLAYER_NAME;
        int max = Config.Values.MAX_LENGTH_PLAYER_NAME;
        int countChara = Console.getHan1Zen2(name);
        return countChara >= min && countChara <= max;
    }

    private boolean isSameName(String name) {
        return name().equals(name);
    }

    private void generatePlayerNo() {
        this.playerNo = maxPlayerNo;
        incrementPlayerNo();
    }

    private void incrementPlayerNo() {
        maxPlayerNo++;
    }

    private void showRequirePlayerName() {
        String requiredMessage = buildMessageForRequirePlayerName();
        Console.print(requiredMessage);
    }

    private String buildMessageForRequirePlayerName() {
        return String.format(Config.MessageFormats.REQUIRE_INPUT_PLAYER_NAME,
                this.playerNo);
    }

    private void showRequireInputInDefinedLength() {
        int min = Config.Values.MIN_LENGTH_PLAYER_NAME;
        int max = Config.Values.MAX_LENGTH_PLAYER_NAME;
        String message = String
                .format(Config.MessageFormats.INPUT_NAME_LENGTH_RULE, min, max);
        Console.println(message);
    }

    private void showRequireInputNonDuplicatedNames() {
        Console.println(Config.Messages.REQUIRE_INPUT_NON_DUPLICATED_NAMES);
    }

    public static Player generatePlayerWithNonDupulicatedNames(
            Players players) {
        return new Player(players);
    }

    public String name() {
        return this.status.name();
    }
}
