package jp.jboocamp.namebattler;

import jp.jboocamp.namebattler.chara.Players;
import jp.jboocamp.namebattler.status.TurnCounter;
import jp.jboocamp.namebattler.util.InputReciever;

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
            InputReciever.recieveEnterKey();
            TurnCounter.countUp();
        }
        this.players.showVictoryPlayer();
        this.players.showAllPlayersAllStatusValues();
    }
}
