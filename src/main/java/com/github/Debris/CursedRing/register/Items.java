package com.github.Debris.CursedRing.register;

import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.item.ItemCursedRing;
import net.minecraft.Item;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Items {
    private static int getNextItemID() {
        return IdUtil.getNextItemID();
    }

    public static final Item cursedRing = new ItemCursedRing(getNextItemID());

    public static void registerItems(ItemRegistryEvent event) {
        event.register(CursedRing.MOD_ID, "cursed_ring", cursedRing);
    }
}
