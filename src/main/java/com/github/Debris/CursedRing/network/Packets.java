package com.github.Debris.CursedRing.network;

import com.github.Debris.CursedRing.CursedRing;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;

public class Packets {
    public static final ResourceLocation OpenEnderChest = new ResourceLocation(CursedRing.MOD_ID, "OpenEnderChest");
    public static final ResourceLocation OpenWindow = new ResourceLocation(CursedRing.MOD_ID, "OpenWindow");

    public static void register() {
        PacketReader.registerServerPacketReader(OpenEnderChest, packetByteBuf -> new C2SOpenEnderChest());

        PacketReader.registerClientPacketReader(OpenWindow, S2COpenWindow::new);
    }
}
