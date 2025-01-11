package com.github.Debris.CursedRing.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.config.CursedRingConfig;
import net.minecraft.*;

import java.util.List;
import java.util.stream.IntStream;

public class ItemCursedRing extends Item implements IBauble {

    public ItemCursedRing(int id) {
        super(id, Material.adamantium, null);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);
        if (extended_info) {
            IntStream.rangeClosed(1, 18).boxed()
                    .map(x -> {
                        if (CursedRing.InfoMap.keySet().contains(x)) {
                            return I18n.getStringParams("tooltip.enigmaticlegacy.cursedRing" + x, CursedRing.InfoMap.get(x).get());
                        }
                        return I18n.getString("tooltip.enigmaticlegacy.cursedRing" + x);
                    })
                    .forEach(x -> info.add(x));
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (CursedRingConfig.EternalFlame.getBooleanValue() && entityLivingBase.isBurning()) {
            entityLivingBase.fire += 20;// fire never extinguish
        }
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return entityLivingBase instanceof EntityPlayer player && player.inCreativeMode();
    }

    @Override
    public boolean dropBaubleOnDeath() {
        return false;
    }
}
