package com.github.Debris.CursedRing;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.config.CursedRingInit;
import com.github.Debris.CursedRing.register.EventListener;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.fml.ModResourceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CursedRing implements ModInitializer {
    public static final String MOD_NAME = "CursedRing";
    public static final int ButtonID = MOD_NAME.hashCode();
    public static final ResourceLocation EnderChest = new ResourceLocation("cursedring", "textures/gui/ender_chest_button.png");
    public static final Map<Integer, Supplier<String>> InfoMap = new HashMap<>() {{
        this.put(6, () -> castDoubleToPercent(CursedRingConfig.ArmorWeakened.getDoubleValue()));
        this.put(7, () -> castDoubleToPercent(CursedRingConfig.DamageToMobWeakened.getDoubleValue()));
        this.put(12, () -> String.valueOf(CursedRingConfig.ExtraLoot.getIntegerValue()));
        this.put(13, () -> String.valueOf(CursedRingConfig.ExtraFortune.getIntegerValue()));
        this.put(14, () -> CursedRingConfig.ExtraEXP.getIntegerValue() + "00%");
        this.put(15, () -> castDoubleToPercent(CursedRingConfig.ExtraEnchant.getDoubleValue()));
    }};

    public static String castDoubleToPercent(double value) {
        int i = (int) (value * 100);
        return i + "%";
    }

    @Override
    public void onInitialize() {
        EventListener.register();
        InitializationHandler.getInstance().registerInitializationHandler(new CursedRingInit());
        ModResourceManager.addResourcePackDomain("cursedring");
    }
}