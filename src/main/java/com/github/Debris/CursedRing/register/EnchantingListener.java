package com.github.Debris.CursedRing.register;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.util.PlayerUtil;
import moddedmite.rustedironcore.api.event.listener.IEnchantingListener;
import net.minecraft.ContainerEnchantment;

public class EnchantingListener implements IEnchantingListener {
    @Override
    public int onEnchantLevelModify(ContainerEnchantment containerEnchantment, int slot_index, int enchantment_levels) {
        if (PlayerUtil.isCursedRingWorn(containerEnchantment.player)) {
            enchantment_levels = (int) (enchantment_levels * (1.0D + CursedRingConfig.ExtraEnchant.getDoubleValue()));
        }
        return enchantment_levels;
    }
}
