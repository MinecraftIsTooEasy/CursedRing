package com.github.Debris.CursedRing.mixins.entity.player;

import baubles.api.BaubleSlotHelper;
import baubles.api.BaublesApi;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.Debris.CursedRing.api.IEntityPlayer;
import com.github.Debris.CursedRing.config.CursedRingConfig;
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
        return BaubleSlotHelper.hasRingOfType((EntityPlayer) (Object) this, CursedRingRegistryInit.cursedRing);
    }

    @Override
    public void cr$TryAddCursedRing()
    {
        if (!this.onServer()) return;

        if (CursedRingConfig.MandatoryWear.getBooleanValue())
        {
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer) (Object) this);

            if (baubles == null) return;

            int ring1 = BaubleSlotHelper.RING_SLOT_1;
            int ring2 = BaubleSlotHelper.RING_SLOT_2;

            if (baubles.getStackInSlot(ring1) == null)
            {
                baubles.setInventorySlotContents(ring1, new ItemStack(CursedRingRegistryInit.cursedRing));

            }
            else if (baubles.getStackInSlot(ring2) == null)
            {
                baubles.setInventorySlotContents(ring2, new ItemStack(CursedRingRegistryInit.cursedRing));
            }

            PlayerHandler.setPlayerBaubles((EntityPlayer) (Object) this, (InventoryBaubles) baubles);
        }
        else
        {
            inventory.addItemStackToInventory(new ItemStack(CursedRingRegistryInit.cursedRing));
        }
    }

}