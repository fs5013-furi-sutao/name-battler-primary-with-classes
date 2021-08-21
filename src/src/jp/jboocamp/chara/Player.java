package src.jp.jboocamp.chara;

import src.jp.jboocamp.setting.Config;
import src.jp.jboocamp.status.Damage;
import src.jp.jboocamp.status.Status;

public class Player {
    private static int maxPlayerNo;
    private int playerNo;
    private Status status;
    private Damage damage;

    static {
        maxPlayerNo = Config.Values.START_OF_PLAYER_NO;
    }

    public Player() {
        generatePlayerNo();
        String name = recieveUserInputtedName();
        this.status = new Status(name);
        this.damage = new Damage();
    }

    private String recieveUserInputtedName() {
        return null;
    }

    private void generatePlayerNo() {
        this.playerNo = maxPlayerNo;
        incrementPlayerNo();
    }

    private void incrementPlayerNo() {
        maxPlayerNo++;

    }
}
