package src.jp.jboocamp.chara;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> members;

    public Players() {
        this.members = new ArrayList<>();
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

    public List<Player> toList() {
        return this.members;
    }

    public boolean isLiverOnlyOne() {
        return false;
    }

    public void showAllPlayersAllStatusValues() {
    }

    public void shuffle() {
    }

    public void battleEach() {
    }

    public Players sortAsc() {
        return null;
    }

    public void showVictoryPlayer() {
    }
}
