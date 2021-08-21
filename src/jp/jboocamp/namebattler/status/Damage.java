package jp.jboocamp.namebattler.status;

import java.util.Random;

import jp.jboocamp.namebattler.chara.Player;
import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.Console;

public class Damage {
    private static final Random RANDOM;
    private Player attacker;
    private Player enemy;
    private int value;
    private boolean isCriticalHit;

    static {
        RANDOM = new Random();
    }

    public Damage(Player attacker) {
        this.attacker = attacker;
        this.isCriticalHit = false;
    }

    public void calc() {
        int baseValue = this.attacker.strValue() - this.enemy.defValue();
        this.value = (int) (baseValue * calcRate());
        fixValueToPositive();
        runInCaseCriticalHit();
    }

    private double calcRate() {
        int seed = RANDOM.nextInt(Config.Values.RANDOM_SEED_DAMAGE);
        double lossRate = Config.Values.LOSS_RATE_FOR_DAMAGE;
        double rateLift = Config.Values.RATE_LIFT_VALUE_FOR_DAMAGE;
        return seed * lossRate + rateLift;
    }

    private void runInCaseCriticalHit() {
        if (isCriticalHit()) {
            this.isCriticalHit = true;
            this.value = this.attacker.strValue();
        }
    }

    private boolean isCriticalHit() {
        int lukRange = Config.Values.LUK_FULL_RANGE - this.attacker.lukValue();

        int criticalHitRange = (int) (lukRange
                / Config.Values.ADJUST_VALUE_FOR_CRITICAL_HIT);

        int randomValue = RANDOM.nextInt(lukRange);
        return criticalHitRange > randomValue;
    }

    private void fixValueToPositive() {
        if (this.value < 0) {
            this.value = 0;
        }
    }

    public void reflect() {
        this.enemy.hp().subtract(this.value);
    }

    public void showDamage() {
        String message = String.format(
                Config.MessageFormats.ENEMY_RECIEVED_DAMAGE, this.enemy.name(),
                this.value, this.enemy.previousHpValue(), this.enemy.hpValue());

        Console.printlnSlow(message);
    }

    public void defineEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Player attacker() {
        return this.attacker;
    }

    public Player enemy() {
        return this.enemy;
    }

    public void showInCaseCritialHit() {
        if (isCriticalHit) {
            Console.println(Config.Messages.CRITICAL_HIT);
        }
    }

    public void initIsCriticalhit() {
        this.isCriticalHit = false;
    }

    public void showInCaseAttackDissmiss() {
        if (this.value == 0) {
            Console.println(Config.Messages.ATTACK_DISMISS);
        }
    }

    public void runInCaseDead() {
        if (!this.enemy.isLive()) {
            String message = String.format(Config.MessageFormats.ENEMY_IS_DIE,
                    this.enemy.name());
            Console.println(message);
        }
    }
}
