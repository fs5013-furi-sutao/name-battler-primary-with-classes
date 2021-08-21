package src.jp.jboocamp.namebattler.setting;

public class Config {
    public class Values {
        public static final int START_OF_PLAYER_NO = 1;
        public static final int NUM_OF_PLAYERS = 4;

        public static final int MIN_LENGTH_PLAYER_NAME = 1;
        public static final int MAX_LENGTH_PLAYER_NAME = 24;

        public static final int SIZE_OF_HANKAKU = 1;
        public static final int SIZE_OF_ZENKAKU = 2;
    }

    public class Messages {
        public static final String REQUIRE_INPUT_NON_DUPLICATED_NAMES = "同じ名前は使用しないでください";
    }

    public class MessageFormats {
        public static final String REQUIRE_INPUT_PLAYER_NAME = "プレイヤー %d の名前を入力してください: ";
        public static final String INPUT_NAME_LENGTH_RULE = "名前は半角 %d 文字以上 %d 文字までで入力してください（全角1文字は半角2文字で換算）";
    }
}