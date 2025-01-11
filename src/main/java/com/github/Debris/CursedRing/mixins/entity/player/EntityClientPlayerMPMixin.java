package com.github.Debris.CursedRing.mixins.entity.player;

import com.github.Debris.CursedRing.util.PlayerUtil;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin extends ClientPlayer {
    public EntityClientPlayerMPMixin(Minecraft par1Minecraft, World par2World, Session par3Session, int par4) {
        super(par1Minecraft, par2World, par3Session, par4);
    }

    @ModifyReturnValue(method = "isSleeping", at = @At("RETURN"))
    private boolean canNotSleep(boolean original) {
        if (PlayerUtil.isCursedRingWorn(this)) return false;
        return original;
    }
}
