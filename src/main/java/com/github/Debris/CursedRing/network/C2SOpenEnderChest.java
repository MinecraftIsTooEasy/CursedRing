package com.github.Debris.CursedRing.network;

import com.github.Debris.CursedRing.api.IServerPlayer;
import com.github.Debris.CursedRing.tileEntity.DummyEnderChest;
import com.github.Debris.CursedRing.util.PlayerUtil;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryEnderChest;
import net.minecraft.ResourceLocation;

public class C2SOpenEnderChest implements Packet {
    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (PlayerUtil.isCursedRingWorn(entityPlayer)) {
            InventoryEnderChest inventory = entityPlayer.getInventoryEnderChest();
            if (inventory != null) {
                inventory.setAssociatedChest(new DummyEnderChest());
                ((IServerPlayer) entityPlayer.getAsEntityPlayerMP()).cr$DisplayEnderChest(inventory);
            }
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return Packets.OpenEnderChest;
    }
}
