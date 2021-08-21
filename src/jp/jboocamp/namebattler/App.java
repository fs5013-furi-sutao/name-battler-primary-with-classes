package jp.jboocamp.namebattler;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.InputReciever;

public class App {
    private static final int NUM_OF_PLAYERS = Config.Values.NUM_OF_PLAYERS;

    public static void main(String[] args) {
        BattleField field = new BattleField(NUM_OF_PLAYERS);
        field.battle();
        InputReciever.shutDown();
    }
}
