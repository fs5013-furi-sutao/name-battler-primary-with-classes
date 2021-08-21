package jp.jboocamp.namebattler.util;

import java.util.Scanner;

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
}
