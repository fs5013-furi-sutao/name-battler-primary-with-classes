package src.jp.jboocamp.namebattler;

import src.jp.jboocamp.namebattler.chara.Players;
import src.jp.jboocamp.namebattler.status.TurnCounter;

public class BattleField {
    private Players players;

    public BattleField(int numOfPlayers) {
        this.players = new Players(numOfPlayers);
    }

    public void battle() {
        while (!this.players.isLiverOnlyOne()) {
            TurnCounter.showTurn();
            this.players.showAllPlayersAllStatusValues();
            this.players.shuffle();
            this.players.battleEach();
            this.players = this.players.sortAsc();
            TurnCounter.countUp();
        }
        this.players.showVictoryPlayer();
        this.players.showAllPlayersAllStatusValues();
    }
}
