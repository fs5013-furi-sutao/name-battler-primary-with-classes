package jp.jboocamp.namebattler.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import jp.jboocamp.namebattler.setting.Config;

public class ValueGenerator {
    public static final int MAX_HASH_VALUE = Config.Values.MAX_HASH_VALUE;
    public static final int HEX = Config.Values.HEX;
    public static final int HASH_CHUNK_LENGTH = Config.Values.HASH_CHUNK_LENGTH;

    /**
    * ハッシュダイジェストから数値を取り出す
    * @param name 名前
    * @param index 何番目の数値を取り出すか
    * @return 数値(0 ～ 255)
    */
    public static int generateNumber(String name, int index) {
        try {
            String digest = getHashDigest(name);
            int hexCunk = index * HASH_CHUNK_LENGTH;
            String hex = digest.substring(hexCunk, hexCunk + HASH_CHUNK_LENGTH);
            return Integer.parseInt(hex, HEX);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getHashDigest(String name) {
        try {
            byte[] result = MessageDigest.getInstance("SHA-1")
                    .digest(name.getBytes());
            return String.format("%040x", new BigInteger(1, result));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
