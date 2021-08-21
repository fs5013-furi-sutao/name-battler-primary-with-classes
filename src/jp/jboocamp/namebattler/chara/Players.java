package jp.jboocamp.namebattler.chara;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.Console;

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

    public Players(List<Player> players) {
        this.members = players;
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
        Console.printBalnk();
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
        this.members = this.members.stream()
                .sorted(Comparator.comparing(Player::playerNo))
                .collect(Collectors.toList());
        return new Players(this.members);
    }

    public void showVictoryPlayer() {
        for (Player player : this.members) {

            if (player.isLive()) {
                String message = String.format(Config.Messages.VICTORY,
                        player.name());
                Console.println(message);
            }
        }
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
