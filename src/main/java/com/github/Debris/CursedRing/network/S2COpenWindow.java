package com.github.Debris.CursedRing.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;

public class S2COpenWindow implements Packet {
    private final int windowId;
    private final String windowTitle;
    private final int slotsCount;
    private final boolean useProvidedWindowTitle;

    public S2COpenWindow(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt(), packetByteBuf.readString(), packetByteBuf.readInt(), packetByteBuf.readBoolean());
    }

    public S2COpenWindow(int windowId, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        if (windowTitle == null) windowTitle = "";
        this.windowId = windowId;
        this.windowTitle = windowTitle;
        this.slotsCount = slotsCount;
        this.useProvidedWindowTitle = useProvidedWindowTitle;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.windowId);
        packetByteBuf.writeString(this.windowTitle);
        packetByteBuf.writeInt(this.slotsCount);
        packetByteBuf.writeBoolean(this.useProvidedWindowTitle);
    }

    @Override
    public void apply(EntityPlayer player) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiChest(player, new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
        player.openContainer.windowId = this.windowId;
    }

    @Override
    public ResourceLocation getChannel() {
        return Packets.OpenWindow;
    }
}
