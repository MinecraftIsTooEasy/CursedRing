package com.github.Debris.CursedRing.mixins.entity;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.util.PlayerUtil;
import net.minecraft.Entity;
import net.minecraft.EntityFishHook;
import net.minecraft.EntityPlayer;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityFishHook.class)
public abstract class EntityFishHookMixin extends Entity {
    @Shadow
    public EntityPlayer angler;

    public EntityFishHookMixin(World par1World) {
        super(par1World);
    }

    @ModifyArg(method = "catchFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityXPOrb;<init>(Lnet/minecraft/World;DDDI)V"), index = 4)
    private int moreXP(int par8) {
        if (PlayerUtil.isCursedRingWorn(this.angler)) {
            par8 *= 1 + CursedRingConfig.ExtraEXP.getIntegerValue();
        }
        return par8;
    }
}
