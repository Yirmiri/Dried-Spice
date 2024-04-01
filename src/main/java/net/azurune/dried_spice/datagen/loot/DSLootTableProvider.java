package net.azurune.dried_spice.datagen.loot;

import net.azurune.dried_spice.datagen.DSBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class DSLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(DSBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
