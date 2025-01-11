package com.github.Debris.CursedRing.config;

import com.github.Debris.CursedRing.CursedRing;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.*;

import java.util.ArrayList;
import java.util.List;

public class CursedRingConfig extends SimpleConfigs {
    private static final CursedRingConfig Instance;

    private static final List<ConfigBase<?>> values;
    private static final List<ConfigBase<?>> number;
    private static final List<ConfigBase<?>> appearance;
    private static final List<ConfigHotkey> hotkeys;


    // values
    public static final ConfigDouble DamageReceivedRate = new ConfigDouble("受伤害倍率", 2.0D, 1.0D, 1024.0D, false, null);
    public static final ConfigDouble ArmorWeakened = new ConfigDouble("盔甲效力削弱幅度", 0.3D, 0.0D, 1.0D, true, null);
    public static final ConfigDouble DamageToMobWeakened = new ConfigDouble("对怪物伤害降低幅度", 0.5D, 0.0D, 1.0D, true, null);
    public static final ConfigInteger ExtraLoot = new ConfigInteger("额外抢夺等级", 1, 0, 1024, false, null);
    public static final ConfigInteger ExtraButcher = new ConfigInteger("额外屠宰等级", 1, 0, 1024, false, null);
    public static final ConfigInteger ExtraFortune = new ConfigInteger("额外时运等级", 1, 0, 1024, false, null);
    public static final ConfigInteger ExtraEXP = new ConfigInteger("经验增加倍率", 4, 0, 1024, false, null);
    public static final ConfigDouble ExtraEnchant = new ConfigDouble("附魔能力增加倍率", 0.33D, 0.0D, 1024.0D, false, null);
    public static final ConfigBoolean NeutralRage = new ConfigBoolean("中立生物的愤怒", true);
    public static final ConfigBoolean EternalFlame = new ConfigBoolean("永恒火焰", true);

    public static final ConfigInteger EnderChestIconOffsetX = new ConfigInteger("末影箱图标X轴偏移", 0);
    public static final ConfigInteger EnderChestIconOffsetY = new ConfigInteger("末影箱图标Y轴偏移", 0);


    // hotkey
    public static final ConfigHotkey OpenEnderChest = new ConfigHotkey("打开末影箱", "P", null);

    public CursedRingConfig(String name, List<ConfigHotkey> hotkeys, List<?> values) {
        super(name, hotkeys, values);
    }

    public static CursedRingConfig getInstance() {
        return Instance;
    }

    public static final List<ConfigTab> tabs = new ArrayList<>();

    @Override
    public List<ConfigTab> getConfigTabs() {
        return tabs;
    }

    static {
        number = List.of(DamageReceivedRate, ArmorWeakened, DamageToMobWeakened, ExtraLoot, ExtraButcher, ExtraFortune, ExtraEXP, ExtraEnchant, NeutralRage, EternalFlame);
        appearance = List.of(EnderChestIconOffsetX, EnderChestIconOffsetY);
        hotkeys = List.of(OpenEnderChest);
        tabs.add(new ConfigTab("数值", number));
        tabs.add(new ConfigTab("外观", appearance));
        tabs.add(new ConfigTab("热键", hotkeys));
        values = new ArrayList<>();
        values.addAll(number);
        values.addAll(appearance);
        Instance = new CursedRingConfig(CursedRing.MOD_ID, hotkeys, values);
    }
}
