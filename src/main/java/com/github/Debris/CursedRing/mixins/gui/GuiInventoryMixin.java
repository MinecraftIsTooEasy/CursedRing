package com.github.Debris.CursedRing.mixins.gui;

import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.config.CursedRingConfig;
import com.github.Debris.CursedRing.gui.GuiEnderChestButton;
import com.github.Debris.CursedRing.network.C2SOpenEnderChest;
import moddedmite.rustedironcore.api.util.FabricUtil;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer {
    public GuiInventoryMixin(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "initGui", at = @At("RETURN"))
    private void addEnderChestButton(CallbackInfo ci) {
        int xSize = 176;
        int ySize = 166;
        int guiLeft = (this.width - xSize) / 2;
        int guiTop = (this.height - ySize) / 2;
        if ((!this.mc.thePlayer.getActivePotionEffects().isEmpty() || this.mc.thePlayer.isMalnourished() || this.mc.thePlayer.isInsulinResistant() || this.mc.thePlayer.is_cursed) && FabricUtil.isModLoaded("extragui")) {
            guiLeft = 160 + (this.width - xSize - 200) / 2;
        }
        int realX = guiLeft + xSize - 27 + CursedRingConfig.EnderChestIconOffsetX.getIntegerValue();
        int realY = guiTop + 60 + CursedRingConfig.EnderChestIconOffsetY.getIntegerValue();
        this.buttonList.add(new GuiEnderChestButton(realX, realY));
    }

    @Inject(method = "actionPerformed", at = @At("RETURN"))
    private void addAction(GuiButton par1GuiButton, CallbackInfo ci) {
        if (par1GuiButton.id == CursedRing.ButtonID) {
            Network.sendToServer(new C2SOpenEnderChest());
        }
    }
}
