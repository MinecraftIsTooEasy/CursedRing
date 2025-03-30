package com.github.Debris.CursedRing.register;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.util.PlayerUtil;
import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import net.minecraft.*;

public class CombatListener implements ICombatListener {
    @Override
    public void onPlayerReceiveDamageModify(EntityPlayer player, Damage damage) {
        if (PlayerUtil.isCursedRingWorn(player)) {
            damage.scaleAmount(1.0F + (float) CursedRingConfig.DamageReceivedRate.getDoubleValue());
        }
    }

    @Override
    public float onPlayerReceiveKnockBackModify(EntityPlayer player, Entity attacker, float original) {
        if (PlayerUtil.isCursedRingWorn(player)) {
            original *= (float) CursedRingConfig.KnockBackReceiveRate.getDoubleValue();
        }
        return original;
    }

    @Override
    public float onArmorProtectionModify(ItemStack item_stack, EntityLivingBase owner, float original) {
        if (owner instanceof EntityPlayer player && PlayerUtil.isCursedRingWorn(player)) {
            original *= (float) (1.0D - CursedRingConfig.ArmorWeakened.getDoubleValue());
        }
        return original;
    }

    @Override
    public void onMobReceiveDamageModify(EntityMob mob, Damage damage) {
        Entity responsibleEntity = damage.getSource().getResponsibleEntity();
        if (responsibleEntity instanceof EntityPlayer player && PlayerUtil.isCursedRingWorn(player)) {
            damage.scaleAmount((float) (1.0D - CursedRingConfig.DamageToMobWeakened.getDoubleValue()));
        }
    }
}
