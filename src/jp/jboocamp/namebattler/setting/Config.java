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

        public static final int RANDOM_SEED_DAMAGE = 5;
        public static final double RATE_LIFT_VALUE_FOR_DAMAGE = 0.6;
        public static final double LOSS_RATE_FOR_DAMAGE = 0.1;

        public static final int LUK_FULL_RANGE = 300;
        public static final int ADJUST_VALUE_FOR_CRITICAL_HIT = 10;

        public static final int SLOW_CONSOLE_WAIT_TIME = 10;
    }

    public class Messages {
        public static final String REQUIRE_INPUT_NON_DUPLICATED_NAMES = "同じ名前は使用しないでください";
        public static final String CRITICAL_HIT = "会心の一撃！";
        public static final String ATTACK_DISMISS = "攻撃をミスした";
        public static final String VICTORY = "%s の勝利！！";
        public static final String PRESS_ENTER_KEY = "<Press Enter Key>";
    }

    public class MessageFormats {
        public static final String REQUIRE_INPUT_PLAYER_NAME = "プレイヤー %d の名前を入力してください: ";
        public static final String INPUT_NAME_LENGTH_RULE = "名前は半角 %d 文字以上 %d 文字までで入力してください（全角1文字は半角2文字で換算）";
        public static final String TURN_LABEL = "ターン %d :==========";
        public static final String PLAYER_NAME_IS = "プレイヤー%d: %s %s";
        public static final String ALL_STATUS_VALUES = "[HP:%3d], [STR:%3d], [DEF:%3d], [LUCK:%3d]";
        public static final String ATTACKER_ATTACK = "%s の攻撃！";
        public static final String ENEMY_RECIEVED_DAMAGE = "%s に %d のダメージ！（HP:%3d ⇒ %3d）";
        public static final String ENEMY_IS_DIE = "%s は力尽きた...";
    }
}
