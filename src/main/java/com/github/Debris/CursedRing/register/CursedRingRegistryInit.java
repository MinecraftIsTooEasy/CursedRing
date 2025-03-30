package com.github.Debris.CursedRing.register;

import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.item.ItemCursedRing;
import huix.glacier.api.entrypoint.IGameRegistry;
import huix.glacier.api.registry.MinecraftRegistry;
import net.minecraft.Item;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class CursedRingRegistryInit implements IGameRegistry {
    public static final MinecraftRegistry registry = new MinecraftRegistry(CursedRing.MOD_NAME).initAutoItemRegister();

    private static int getNextItemID() {
        return IdUtil.getNextItemID();
    }

    public static final Item cursedRing = new ItemCursedRing(getNextItemID());

    @Override
    public void onGameRegistry() {
        registry.registerItem("cursedring:cursed_ring", "cursed_ring", cursedRing);
    }
}
