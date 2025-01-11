package com.github.Debris.CursedRing.mixins.entity;

import com.github.Debris.CursedRing.util.PlayerUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.EntityEnderman;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin {
    @WrapOperation(method = "shouldAttackPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityEnderman;isEnderPearlOrEye(Lnet/minecraft/ItemStack;)Z"))
    private boolean angryAtThoseCursed(ItemStack item_stack, Operation<Boolean> original, @Local(argsOnly = true) EntityPlayer player) {
        if (PlayerUtil.shouldBeAngryAt(player)) {
            return true;
        }
        return original.call(item_stack);
    }
}
