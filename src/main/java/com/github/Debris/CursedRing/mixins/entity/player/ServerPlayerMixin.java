package com.github.Debris.CursedRing.mixins.entity.player;

import com.github.Debris.CursedRing.api.IServerPlayer;
import com.github.Debris.CursedRing.network.S2COpenWindow;
import com.github.Debris.CursedRing.util.PlayerUtil;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends EntityPlayer implements IServerPlayer, ICrafting {
    @Shadow
    protected abstract void incrementWindowID();

    @Shadow
    private int currentWindowId;

    @Shadow public abstract void addChatMessage(String par1Str);

    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Inject(method = "tryToSleepInBedAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/ServerPlayer;getIntoBed(IIII)V"))
    private void addMessage(int x, int y, int z, CallbackInfo ci) {
        this.addChatMessage("cursedRing.sleep.message");
    }

    @Override
    public void cr$DisplayEnderChest(IInventory inventory) {
        if (this.openContainer != this.inventoryContainer) {
            this.closeScreen();
        }
        this.incrementWindowID();
        Network.sendToClient(this.getAsEntityPlayerMP(), new S2COpenWindow(this.currentWindowId, inventory.getCustomNameOrUnlocalized(),inventory.getSizeInventory() ,inventory.hasCustomName()));
        this.openContainer = new ContainerChest(this, inventory);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    @ModifyReturnValue(method = "isSleeping", at = @At("RETURN"))
    private boolean canNotSleep(boolean original) {
        if (PlayerUtil.isCursedRingWorn(this)) return false;
        return original;
    }
}
