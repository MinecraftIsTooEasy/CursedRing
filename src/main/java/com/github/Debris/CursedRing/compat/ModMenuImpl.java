package com.github.Debris.CursedRing.compat;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> CursedRingConfig.getInstance().getConfigScreen(screen);
    }
}
