package com.github.Debris.CursedRing;

import com.github.Debris.CursedRing.config.CursedRingConfig;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class EarlyRiser implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        CursedRingConfig.getInstance().load();
    }
}
