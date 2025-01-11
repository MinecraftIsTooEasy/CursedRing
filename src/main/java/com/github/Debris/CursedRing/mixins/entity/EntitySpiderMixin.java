package com.github.Debris.CursedRing.mixins.entity;

import com.github.Debris.CursedRing.util.PlayerUtil;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityArachnid.class)
public abstract class EntitySpiderMixin extends EntityMob {
    public EntitySpiderMixin(World par1World) {
        super(par1World);
    }

    @WrapWithCondition(method = "checkSwitchingToPeaceful", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityArachnid;setEntityToAttack(Lnet/minecraft/Entity;)V"))
    private boolean doNotStop(EntityArachnid instance, Entity entity) {
        if (this.getEntityToAttack() instanceof EntityPlayer player && PlayerUtil.shouldBeAngryAt(player)) {
            return false;
        }
        return true;
    }
}
