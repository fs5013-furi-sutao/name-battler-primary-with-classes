package jp.jboocamp.namebattler.util;

import java.util.Scanner;

import jp.jboocamp.namebattler.setting.Config;

public class InputReciever {
    private static final Scanner STDIN;

    static {
        STDIN = new Scanner(System.in);
    }

    public static String recieveInputtedStr(String message) {
        message += Config.Messages.WAIT_FOR_USER_INPUT_MARK;
        Console.print(message);
        return STDIN.nextLine();
    }

    public static String recieveInputtedStrWithoutMark(String message) {
        Console.print(message);
        return STDIN.nextLine();
    }

    public static void shutDown() {
        STDIN.close();
    }

    public static void recieveEnterKey() {
        String message = Config.Messages.PRESS_ENTER_KEY;
        String inputtedStr = recieveInputtedStrWithoutMark(message);

        while (inputtedStr != null) {
            if (inputtedStr.isEmpty()) {
                Console.printBalnk();
                return;
            }

            if (STDIN.hasNextLine()) {
                inputtedStr = recieveInputtedStrWithoutMark(message);
                continue;
            }
            inputtedStr = null;
        }
    }

    public static int recieveInputtedNum(String message, int min, int max) {
        String inputtedNumStr = recieveInputtedStr(message);
        int inputtedNum = validateInputtedNumInRange(message, inputtedNumStr, min, max);
        return inputtedNum;
    }

    private static int validateInputtedNumInRange(String message, String str, int min, int max) {
        if (!isNum(str)) {
            Console.println(Config.Messages.REQUIRE_INPUT_WITH_NUM);
            return recieveInputtedNum(message, min, max);
        }

        int inputtedNum = parseInt(str);

        if (!isInRange(min, max,inputtedNum)) {
            showRequireInputNumInRange(min, max);
            return recieveInputtedNum(message, min, max);
        }
        return inputtedNum;
    }

    private static void showRequireInputNumInRange(int min, int max) {
        String message = String.format(
            Config.MessageFormats.REQUIRE_INPUT_NUM_IN_RANGE, 
            min, 
            max);

        Console.println(message);
    }

    private static boolean isNum(String str) {
        try {
            parseInt(str);

        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isInRange(int min, int max, int num) {
        return num >= min && num <= max;
    }

    private static int parseInt(String str) {
        return Integer.parseInt(str);
    }

}
