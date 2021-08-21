package jp.jboocamp.namebattler.util;

import java.nio.charset.Charset;
import java.util.Random;

import jp.jboocamp.namebattler.setting.Config;

public class Console {
    private static final Random RANDOM;

    static {
        RANDOM = new Random();
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printlnSlow(String message) {
        char[] charArray = message.toCharArray();
        for (char chara : charArray) {
            print(String.valueOf(chara));
            waitRandomTime(Config.Values.SLOW_CONSOLE_WAIT_TIME);
        }
        printBalnk();
    }

    public static void printBalnk() {
        System.out.println();
    }

    /**
     * 全角文字は2桁 半角文字は1桁として文字数をカウントする
     * 
     * @param str 対象文字列
     * @return 文字数
     */
    public static int getHan1Zen2(String str) {
        int ret = 0;

        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {

            if (String.valueOf(c[i]).getBytes().length <= 1) {
                ret += Config.Values.SIZE_OF_HANKAKU;
                continue;
            }
            ret += Config.Values.SIZE_OF_ZENKAKU;
        }
        return ret;
    }

    private static int getByteLength(String string, Charset charset) {
        return string.getBytes(charset).length;
    }

    public static String adjustWidth(String target, int length) {
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
        return String.format("%-" + (length - byteDiff + 1) + "s", target);
    }

    public static void waitRandomTime(int millisSeconds) {
        try {
            Thread.sleep(RANDOM.nextInt(millisSeconds));
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
