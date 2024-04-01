package net.azurune.dried_spice.compat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class DSCompatRegistries {
    public static final boolean excessive_building;

    static {
        ModList mods = ModList.get();
        excessive_building = mods.isLoaded("excessive_building");
    }

    public static void register(IEventBus eventBus) {
        if (excessive_building) ExcessiveBuildingCompat.register(eventBus);
    }
}
