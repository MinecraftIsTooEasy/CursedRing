package com.github.Debris.CursedRing.mixins.entity.player;

import baubles.api.BaublesApi;
import com.github.Debris.CursedRing.api.IEntityPlayer;
import com.github.Debris.CursedRing.register.CursedRingRegistryInit;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements IEntityPlayer {
    @Shadow public InventoryPlayer inventory;

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Override
    public boolean cr$IsCursedRingWorn() {
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer) (Object) this);
        for (int i = 0; i < baubles.getSizeInventory(); i++) {
            ItemStack stackInSlot = baubles.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() == CursedRingRegistryInit.cursedRing) return true;
        }
        return false;
    }

    @Override
    public void cr$TryAddCursedRing() {
        if (this.onServer()) {
//            IInventory baubles = BaublesApi.getBaubles((EntityPlayer) (Object) this);
//            for (int i = 1; i <= 2; i++) {// only consider them
//                if (baubles.getStackInSlot(i) == null) {
//                    baubles.setInventorySlotContents(i, new ItemStack(Items.cursedRing));
//                    break;
//                }
//            }
//            PlayerHandler.setPlayerBaubles((EntityPlayer) (Object) this, (InventoryBaubles) baubles);
            inventory.addItemStackToInventory(new ItemStack(CursedRingRegistryInit.cursedRing));
        }
    }
}
