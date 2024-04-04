package net.azurune.dried_spice.compat;

import net.azurune.dried_spice.block.StoveBlock;
import net.azurune.dried_spice.uti.DSProperties;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class ExcessiveBuildingCompat {

    //BLOCKS
    public static final RegistryObject<Block> SOUL_STOVE = DSBlocks.register("soul_stove", () -> new StoveBlock(2, DSProperties.BlockProperties.SOUL_STOVE));

    //ITEMS
    public static final RegistryObject<Item> ANCIENT_TEA_LEAVES = DSItems.ITEMS.register("ancient_tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.TEA_LEAVES)));
    public static final RegistryObject<Item> ANCIENT_DRIED_TEA_LEAVES = DSItems.ITEMS.register("ancient_dried_tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.TEA_LEAVES)));

    public static void register(IEventBus eventBus) {
    }
}