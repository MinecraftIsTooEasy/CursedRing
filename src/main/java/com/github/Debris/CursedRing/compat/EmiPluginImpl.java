package com.github.Debris.CursedRing.compat;

import com.github.Debris.CursedRing.CursedRing;
import com.github.Debris.CursedRing.register.Items;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiStack;
import shims.java.net.minecraft.text.Text;

import java.util.List;
import java.util.stream.IntStream;

public class EmiPluginImpl implements EmiPlugin {
    @Override
    public void register(EmiRegistry emiRegistry) {
        List<Text> cursedRingTexts = IntStream.rangeClosed(1, 18).boxed().map(x -> {
            if (CursedRing.InfoMap.keySet().contains(x)) {
                return (Text) Text.translatable("tooltip.enigmaticlegacy.cursedRing" + x, CursedRing.InfoMap.get(x).get());
            }
            return (Text) Text.translatable("tooltip.enigmaticlegacy.cursedRing" + x);
        }).toList();
        emiRegistry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(Items.cursedRing)), cursedRingTexts, null));
    }
}
