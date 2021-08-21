package jp.jboocamp.namebattler.chara;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.status.Damage;
import jp.jboocamp.namebattler.status.Status;
import jp.jboocamp.namebattler.status.value.Hp;
import jp.jboocamp.namebattler.util.Console;
import jp.jboocamp.namebattler.util.InputReciever;

public class Player {
    private static int maxPlayerNo;
    private int playerNo;
    private Status status;
    private Damage damage;

    static {
        maxPlayerNo = Config.Values.START_OF_PLAYER_NO;
    }

    public Player(Players players) {
        generatePlayerNo();
        String name = recieveUserInputtedName(players);
        this.status = new Status(name);
        this.damage = new Damage(this);
    }

    public void attack() {
        showAttackAction();
        this.damage.calc();
        this.damage.reflect();
        this.damage.showInCaseCritialHit();
        this.damage.showInCaseAttackDissmiss();
        this.damage.showDamage();
        this.damage.enemy().updateIsLive();
        this.damage.initIsCriticalhit();
        this.damage.runInCaseDead();
        Console.printBalnk();
    }

    public void encountEnemy(Players players) {

        Player enemy = players.pickOnlyLivePlayers().pickEnemy();
        this.damage.defineEnemy(enemy);

        if (enemy.equals(this)) {
            encountEnemy(players);
        }
    }

    private String recieveUserInputtedName(Players players) {
        String requiredMessage = buildMessageForRequirePlayerName();
        String inputtedName = InputReciever.recieveInputtedStr(requiredMessage);
        inputtedName = validateUserInputtedName(players, inputtedName);
        Console.printBalnk();
        return inputtedName;
    }

    private String validateUserInputtedName(Players players, String name) {
        if (!isLengthInRange(name)) {
            showRequireInputInDefinedLength();
            return recieveUserInputtedName(players);
        }

        for (Player player : players.toList()) {

            if (player.isSameName(name)) {
                showRequireInputNonDuplicatedNames();
                return recieveUserInputtedName(players);
            }
        }
        return name;
    }

    private boolean isLengthInRange(String name) {
        int min = Config.Values.MIN_LENGTH_PLAYER_NAME;
        int max = Config.Values.MAX_LENGTH_PLAYER_NAME;
        int countChara = Console.getHan1Zen2(name);
        return countChara >= min && countChara <= max;
    }

    private boolean isSameName(String name) {
        return name().equals(name);
    }

    private void generatePlayerNo() {
        this.playerNo = maxPlayerNo;
        incrementPlayerNo();
    }

    private void incrementPlayerNo() {
        maxPlayerNo++;
    }

    private String buildMessageForRequirePlayerName() {
        return String.format(Config.MessageFormats.REQUIRE_INPUT_PLAYER_NAME,
                this.playerNo);
    }

    public void showAllStatusValues(Players players) {
        String message = String.format(Config.MessageFormats.PLAYER_NAME_IS,
                this.playerNo, ajustNameCompareOtherPlayers(players),
                statusStr());
        Console.println(message);
    }

    private void showRequireInputInDefinedLength() {
        int min = Config.Values.MIN_LENGTH_PLAYER_NAME;
        int max = Config.Values.MAX_LENGTH_PLAYER_NAME;
        String message = String
                .format(Config.MessageFormats.INPUT_NAME_LENGTH_RULE, min, max);
        Console.println(message);
    }

    private void showRequireInputNonDuplicatedNames() {
        Console.println(Config.Messages.REQUIRE_INPUT_NON_DUPLICATED_NAMES);
    }

    public void showAttackAction() {
        String message = String.format(Config.MessageFormats.ATTACKER_ATTACK,
                name());
        Console.println(message);
    }

    public static Player generatePlayerWithNonDupulicatedNames(
            Players players) {
        return new Player(players);
    }

    private String ajustNameCompareOtherPlayers(Players players) {
        int longestLengthName = players.longestLengthName();
        return Console.adjustWidth(name(), longestLengthName * 2);
    }

    public String statusStr() {
        return this.status.valuesStr();
    }

    public int playerNo() {
        return this.playerNo;
    }

    public String name() {
        return this.status.name();
    }

    public boolean isLive() {
        return this.status.isLive();
    }

    private void updateIsLive() {
        if (hp().isDead()) {
            this.status.toDeath();
        }
    }

    public Hp hp() {
        return this.status.hp();
    }

    public int hpValue() {
        return this.status.hpValue();
    }

    public int previousHpValue() {
        return this.status.previousHpValue();
    }

    public int previousHp() {
        return this.status.previousHp();
    }

    public int strValue() {
        return this.status.strValue();
    }

    public int defValue() {
        return this.status.defValue();
    }

    public int lukValue() {
        return this.status.luckValue();
    }
}
