package com.github.Debris.CursedRing.mixins.entity;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
    @Unique
    private int hitByCursedPlayer;

    public EntityLivingBaseMixin(World par1World) {
        super(par1World);
    }

    @ModifyExpressionValue(method = "onDeathUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLivingBase;getExperienceValue()I"))
    private int addXP(int original) {
        if (this.hitByCursedPlayer > 0 && !this.isEntityPlayer()) {
            original *= 1 + CursedRingConfig.ExtraEXP.getIntegerValue();
        }
        return original;
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/EntityLivingBase;recentlyHit:I", opcode = Opcodes.PUTFIELD))
    private void markHitByCursedPlayer(Damage damage, CallbackInfoReturnable<EntityDamageResult> cir) {
        this.hitByCursedPlayer = 100;
    }

    @Inject(method = "onEntityUpdate", at = @At("RETURN"))
    private void checkMark(CallbackInfo ci) {
        if (this.hitByCursedPlayer > 0) {
            this.hitByCursedPlayer--;
        }
    }

}
