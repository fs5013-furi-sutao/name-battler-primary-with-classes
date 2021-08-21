package src.jp.jboocamp.util;

import src.jp.jboocamp.setting.Config;

public class Console {

    public static void println(String message) {
        System.out.print(message);
    }

    /**
     * 全角文字は2桁 半角文字は1桁として文字数をカウントする
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
}
