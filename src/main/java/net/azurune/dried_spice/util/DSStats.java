package net.azurune.dried_spice.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class DSStats {
    public static ResourceLocation INTERACT_WITH_KETTLE;
    public static ResourceLocation DRINK_DECORATIVE_BOTTLE;

    public static void init() {
        INTERACT_WITH_KETTLE = makeCustomStat("interact_with_kettle", StatFormatter.DEFAULT);
        DRINK_DECORATIVE_BOTTLE = makeCustomStat("drink_decorative_bottle", StatFormatter.DEFAULT);
    }

    private static ResourceLocation makeCustomStat(String string, StatFormatter formatter) {
        ResourceLocation resourcelocation = new ResourceLocation(string);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, string, resourcelocation);
        Stats.CUSTOM.get(resourcelocation, formatter);
        return resourcelocation;
    }
}
