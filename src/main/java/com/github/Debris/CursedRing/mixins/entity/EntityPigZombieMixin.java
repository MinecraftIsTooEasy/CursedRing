package com.github.Debris.CursedRing.mixins.entity;

import com.github.Debris.CursedRing.util.PlayerUtil;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPigZombie.class)
public abstract class EntityPigZombieMixin extends EntityZombie {
    @Shadow
    protected abstract void becomeAngryAt(Entity par1Entity);

    public EntityPigZombieMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void angryAtThoseCursed(CallbackInfo ci) {
        if (this.getEntityToAttack() != null) return;
        if (this.onClient()) return;
        EntityPlayer target = this.getClosestVulnerablePlayer(this.getMaxTargettingRange());
        if (target != null && PlayerUtil.shouldBeAngryAt(target)) {
            this.becomeAngryAt(target);
        }
    }
}
