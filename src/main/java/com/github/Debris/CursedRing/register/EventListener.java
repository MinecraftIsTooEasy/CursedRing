package com.github.Debris.CursedRing.register;

import com.github.Debris.CursedRing.api.IEntityPlayer;
import com.github.Debris.CursedRing.network.Packets;
import com.github.Debris.CursedRing.util.PlayerUtil;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class EventListener extends Handlers {

    public static void register() {
        MITEEvents.MITE_EVENT_BUS.register(new EventListener());

        Packets.register();

        PlayerEvent.register(new IPlayerEventListener() {
            @Override
            public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
                ServerPlayer player = event.player();
                if (!PlayerUtil.isCursedRingWorn(player) && !player.inCreativeMode() && event.firstLogin()) {
                    ((IEntityPlayer) (EntityPlayer) player).cr$TryAddCursedRing();
                }
            }
        });
        Enchanting.register(new EnchantingListener());

        Combat.register(new CombatListener());
    }
}
