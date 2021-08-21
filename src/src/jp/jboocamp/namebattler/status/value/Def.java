package jp.jboocamp.namebattler.status.value;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.ValueGenerator;

/**
 * 防御力を表すクラス
 */
public class Def {
    private int value;

    public Def(String name) {
        init(name);
    }

    public void init(String name) {
        int baseValue = ValueGenerator.generateNumber(name,
                Config.Values.INDEX_FOR_DEF);

        double baseRate = (double) baseValue / ValueGenerator.MAX_HASH_VALUE;
        double defCalcBase = Config.Values.DEF_CALC_BASE;
        this.value = (int) (defCalcBase * baseRate);
    }

    public int value() {
        return this.value;
    }
}
