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
        for (int i = 0; i < numOfPlayers; i++) {
            this.members
                    .add(Player.generatePlayerWithNonDupulicatedNames(this));
        }
    }
}
