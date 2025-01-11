package com.github.Debris.CursedRing.tileEntity;

import net.minecraft.EntityPlayer;
import net.minecraft.TileEntityEnderChest;

public class DummyEnderChest extends TileEntityEnderChest {
    public DummyEnderChest() {
        super();
    }

    @Override
    public void openChest() {
    }

    public void closeChest() {
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return true;
    }
}
