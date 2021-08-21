package jp.jboocamp.namebattler.chara;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Players {
    private static final Random RANDOM;
    private List<Player> members;

    static {
        RANDOM = new Random();
    }

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

    public void showAllPlayersAllStatusValues() {
        for (Player player : this.members) {
            player.showAllStatusValues(this);
        }
    }

    public void shuffle() {
        Collections.shuffle(this.members);
    }

    public void battleEach() {
        for (Player attacker : pickOnlyLivePlayers().toList()) {

            if (!attacker.isLive()) {
                continue;
            }
            attacker.encountEnemy(this);
            attacker.attack();
        }
    }

    public Players pickOnlyLivePlayers() {
        Players onlyLivePlayers = new Players();
        for (Player player : this.members) {
            if (player.isLive()) {
                onlyLivePlayers.join(player);
            }
        }
        return onlyLivePlayers;
    }

    public int getRandomIndex() {
        return RANDOM.nextInt(this.members.size());
    }

    public Player pick(int index) {
        return this.members.get(index);
    }

    public void join(Player player) {
        this.members.add(player);
    }

    public Player pickEnemy() {
        return pick(getRandomIndex());
    }

    public boolean isLiverOnlyOne() {
        int count = 0;
        for (Player player : this.members) {
            if (player.isLive()) {
                count++;
            }
        }
        return count == 1;
    }

    public Players sortAsc() {
        return null;
    }

    public void showVictoryPlayer() {
    }

    public int longestLengthName() {
        int longestLengh = 0;
        for (Player player : this.members) {
            int length = player.name().length();
            if (length > longestLengh) {
                longestLengh = length;
            }
        }
        return longestLengh;
    }
}
