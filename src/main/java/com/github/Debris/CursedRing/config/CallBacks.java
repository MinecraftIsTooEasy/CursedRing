package com.github.Debris.CursedRing.config;

import com.github.Debris.CursedRing.gui.GuiEnderChestButton;
import com.github.Debris.CursedRing.network.C2SOpenEnderChest;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.Minecraft;

public class CallBacks {
    public static void init() {
        CursedRingConfig.OpenEnderChest.getKeybind().setCallback((keyAction, iKeybind) -> {
            if (!GuiEnderChestButton.shouldDraw(Minecraft.getMinecraft())) return false;
            Network.sendToServer(new C2SOpenEnderChest());
            return true;
        });
    }
}
