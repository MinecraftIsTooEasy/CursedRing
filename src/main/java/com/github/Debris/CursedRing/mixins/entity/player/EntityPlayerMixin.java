package com.github.Debris.CursedRing.mixins.entity.player;

import baubles.api.BaublesApi;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.Debris.CursedRing.api.IEntityPlayer;
import com.github.Debris.CursedRing.register.Items;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements IEntityPlayer {
    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Override
    public boolean cursedRing$IsCursedRingWorn() {
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer) (Object) this);
        for (int i = 0; i < baubles.getSizeInventory(); i++) {
            ItemStack stackInSlot = baubles.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() == Items.cursedRing) return true;
        }
        return false;
    }

    @Override
    public void cursedRing$TryAddCursedRing() {
        if (this.onServer()) {
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer) (Object) this);
            for (int i = 1; i <= 2; i++) {// only consider them
                if (baubles.getStackInSlot(i) == null) {
                    baubles.setInventorySlotContents(i, new ItemStack(Items.cursedRing));
                    break;
                }
            }
            PlayerHandler.setPlayerBaubles((EntityPlayer) (Object) this, (InventoryBaubles) baubles);
        }
    }
}
