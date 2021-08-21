package jp.jboocamp.namebattler.status.value;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.ValueGenerator;

/**
 * 体力を表すクラス
 */
public class Hp {
    private int previousValue;
    private int value;

    public Hp(String name) {
        init(name);
    }

    public void init(String name) {
        this.value = ValueGenerator.generateNumber(name, Config.Values.INDEX_FOR_HP);
    }

    public int value() {
        return this.value;
    }

    public int previousValue() {
        return this.previousValue;
    }

    public void subtract(int value) {
        updatePreviousValue();
        this.value -= value;

        if (this.value < 0) {
            this.value = 0;
        }
    }

    private void updatePreviousValue() {
        this.previousValue = this.value;
    }

    public boolean isDead() {
        return value == 0;
    }
}
