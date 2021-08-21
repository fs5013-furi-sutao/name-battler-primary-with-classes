package jp.jboocamp.namebattler.status;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.Console;

public class TurnCounter {
    private static int value;

    static {
        value = Config.Values.START_OF_TURN_COUNT;
    }

    public static void showTurn() {
        String message = String.format(Config.MessageFormats.TURN_LABEL, value);
        Console.println(message);
    }

    public static void countUp() {
        value++;
    }
}
