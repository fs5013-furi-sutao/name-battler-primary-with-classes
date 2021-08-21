package jp.jboocamp.namebattler.setting;

public class Config {
    public class Values {
        public static final int START_OF_TURN_COUNT = 1;
        public static final int START_OF_PLAYER_NO = 1;
        public static final int NUM_OF_PLAYERS = 4;

        public static final int MIN_LENGTH_PLAYER_NAME = 1;
        public static final int MAX_LENGTH_PLAYER_NAME = 24;

        public static final int SIZE_OF_HANKAKU = 1;
        public static final int SIZE_OF_ZENKAKU = 2;

        public static final int HEX = 16;
        public static final int HASH_CHUNK_LENGTH = 2;
        public static final int MAX_HASH_VALUE = 255;

        public static final int INDEX_FOR_HP = 0;
        public static final int INDEX_FOR_STR = 1;
        public static final int INDEX_FOR_DEF = 2;
        public static final int INDEX_FOR_LUK = 3;

        public static final double STR_CALC_BASE = 80;
        public static final double STR_LIFT_VALUE = 20;
        public static final double DEF_CALC_BASE = 50;
        public static final double LUK_CALC_BASE = 100;
    }

    public class Messages {
        public static final String REQUIRE_INPUT_NON_DUPLICATED_NAMES = "同じ名前は使用しないでください";
    }

    public class MessageFormats {
        public static final String REQUIRE_INPUT_PLAYER_NAME = "プレイヤー %d の名前を入力してください: ";
        public static final String INPUT_NAME_LENGTH_RULE = "名前は半角 %d 文字以上 %d 文字までで入力してください（全角1文字は半角2文字で換算）";
        public static final String TURN_LABEL = "ターン %d :==========";
        public static final String ALL_STATUS_VALUES = "[HP:%3d], [STR:%3d], [DEF:%3d], [LUCK:%3d]";
    }
}
