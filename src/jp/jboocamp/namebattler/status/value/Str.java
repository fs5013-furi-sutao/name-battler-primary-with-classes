package jp.jboocamp.namebattler.status.value;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.ValueGenerator;

/**
 * 攻撃力を表すクラス
 */
public class Str {
    private int value;

    public Str(String name) {
        init(name);
    }

    public void init(String name) {
        int baseValue = ValueGenerator.generateNumber(name,
                Config.Values.INDEX_FOR_STR);

        double baseRate = (double) baseValue / ValueGenerator.MAX_HASH_VALUE;
        double strCalcBase = Config.Values.STR_CALC_BASE;
        double strLiftValue = Config.Values.STR_LIFT_VALUE;
        this.value = (int) (strCalcBase * baseRate + strLiftValue);
    }

    public int value() {
        return this.value;
    }
}
