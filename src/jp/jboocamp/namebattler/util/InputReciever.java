package jp.jboocamp.namebattler.util;

import java.util.Scanner;

import jp.jboocamp.namebattler.setting.Config;

public class InputReciever {
    private static final Scanner STDIN;

    static {
        STDIN = new Scanner(System.in);
    }

    public static String recieveInputtedStr() {
        return STDIN.nextLine();
    }

    public static void shutDown() {
        STDIN.close();
    }

    public static void recieveEnterKey() {
        showRequirePressEnterKey();
        String inputtedStr = recieveInputtedStr();

        while (inputtedStr != null) {
            if (inputtedStr.isEmpty()) {
                Console.printBalnk();
                return;
            }

            if (STDIN.hasNextLine()) {
                inputtedStr = recieveInputtedStr();
                continue;
            }
            inputtedStr = null;
        }
    }

    private static void showRequirePressEnterKey() {
        Console.print(Config.Messages.PRESS_ENTER_KEY);
    }
}
