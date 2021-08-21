package src.jp.jboocamp.chara;

import java.util.List;

public class Players {
    private List<Player> members;

    public Players() {
    }

    public Players(int numOfPlayers) {
        this();
        collectAsMuchAsNeed(numOfPlayers);
    }

    private void collectAsMuchAsNeed(int numOfPlayers) {
    }
}
