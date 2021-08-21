package jp.jboocamp.namebattler;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.Console;
import jp.jboocamp.namebattler.util.InputReciever;

public class App {
    public static void main(String[] args) {
        int numOfPlayers = recieveUserInputAsNumOfPlayers(
            Config.Messages.INPUT_NUM_OF_PLAYER);

        BattleField field = new BattleField(numOfPlayers);
        field.battle();
        InputReciever.shutDown();
    }

    private static int recieveUserInputAsNumOfPlayers(String message) {
        int numOfPlayers = InputReciever.recieveInputtedNum(
            message, 
            Config.Values.MIN_NUM_OF_PLAYERS, 
            Config.Values.MAX_NUM_OF_PLAYERS);

        Console.printBalnk();
        return numOfPlayers;
    }
}
