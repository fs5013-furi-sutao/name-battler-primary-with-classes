package src.jp.jboocamp.namebattler;

public class App {
    private static final int NUM_OF_PLAYERS = 3;

    public static void main(String[] args) {
        BattleField field = new BattleField(NUM_OF_PLAYERS);
        field.battle();
    }
}
