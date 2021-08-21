package jp.jboocamp.namebattler.status;

import jp.jboocamp.namebattler.setting.Config;
import jp.jboocamp.namebattler.status.value.Def;
import jp.jboocamp.namebattler.status.value.Hp;
import jp.jboocamp.namebattler.status.value.Luk;
import jp.jboocamp.namebattler.status.value.Str;

public class Status {
    private String name;
    private boolean isLive;
    private Hp hp;
    private Str str;
    private Def def;
    private Luk luk;

    public Status(String name) {
        this.name = name;
        this.isLive = true;
        this.hp = new Hp(name);
        this.str = new Str(name);
        this.def = new Def(name);
        this.luk = new Luk(name);
    }

    public String name() {
        return this.name;
    }

    public boolean isLive() {
        return this.isLive;
    }

    public Hp hp() {
        return this.hp;
    }

    public int hpValue() {
        return hp().value();
    }

    public int previousHp() {
        return this.previousHp();
    }

    public int previousHpValue() {
        return hp().previousValue();
    }

    public Str str() {
        return this.str;
    }

    public int strValue() {
        return this.str.value();
    }

    public Def def() {
        return this.def;
    }

    public int defValue() {
        return this.def.value();
    }

    public int luckValue() {
        return this.luk.value();
    }

    public String valuesStr() {
        return String.format(Config.MessageFormats.ALL_STATUS_VALUES,
                this.hp.value(), this.str.value(), this.def.value(),
                this.luk.value());
    }

    public void toDeath() {
        this.isLive = false;
    }
}
