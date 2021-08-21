package jp.jboocamp.namebattler.status.value;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.util.ValueGenerator;

public class Luk {
    private int value;

    public Luk(String name) {
        init(name);
    }

    public void init(String name) {
        int baseValue = ValueGenerator.generateNumber(name,
                Config.Values.INDEX_FOR_LUK);

        double baseRate = (double) baseValue / ValueGenerator.MAX_HASH_VALUE;
        double lukCalcBase = Config.Values.LUK_CALC_BASE;
        this.value = (int) (lukCalcBase * baseRate);
    }

    public int value() {
        return this.value;
    }
}
