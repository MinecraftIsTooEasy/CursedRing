package com.github.Debris.CursedRing.config;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;

public class CursedRingInit implements IInitializationHandler {
    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance().registerConfig(CursedRingConfig.getInstance());
        CallBacks.init();
    }
}
