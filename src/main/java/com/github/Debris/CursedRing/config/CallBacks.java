package com.github.Debris.CursedRing.config;

import com.github.Debris.CursedRing.network.C2SOpenEnderChest;
import moddedmite.rustedironcore.network.Network;

public class CallBacks {
    public static void init() {
        CursedRingConfig.OpenEnderChest.getKeybind().setCallback((keyAction, iKeybind) -> {
            Network.sendToServer(new C2SOpenEnderChest());
            return true;
        });
    }
}
