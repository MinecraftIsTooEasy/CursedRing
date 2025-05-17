package com.github.Debris.CursedRing.util;

import com.github.Debris.CursedRing.api.IEntityPlayer;
import com.github.Debris.CursedRing.config.CursedRingConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.EntityPlayer;

public class PlayerUtil {
    public static boolean shouldBeAngryAt(EntityPlayer player) {
        return !player.isPlayerInCreative() && CursedRingConfig.NeutralRage.getBooleanValue() && PlayerUtil.isCursedRingWorn(player);
    }

    /**
     * The baubles api is not available at client, at least now
     */
    @Environment(EnvType.SERVER)
    public static boolean isCursedRingWorn(EntityPlayer player) {
        return ((IEntityPlayer) (EntityPlayer) player).cr$IsCursedRingWorn();
    }

}
