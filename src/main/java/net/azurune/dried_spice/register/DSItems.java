package net.azurune.dried_spice.register;

import net.azurune.dried_spice.other.DSProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.azurune.dried_spice.DriedSpice;
import net.minecraftforge.registries.RegistryObject;

public class DSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DriedSpice.MODID);

    //INGREDIENTS
    public static final RegistryObject<Item> TEA_LEAVES = ITEMS.register("tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.TEA_LEAVES)));
    public static final RegistryObject<Item> DRIED_TEA_LEAVES = ITEMS.register("dried_tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.TEA_LEAVES)));
    public static final RegistryObject<Item> MUD_CUP = ITEMS.register("mud_cup", () -> new Item(new Item.Properties()));

    //NATURAL
    public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds", () -> new ItemNameBlockItem(DSBlocks.PEPPER_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SOUL_PEPPER_SEEDS = ITEMS.register("soul_pepper_seeds", () -> new ItemNameBlockItem(DSBlocks.SOUL_PEPPER_CROP.get(), new Item.Properties()));

    //FOODS
    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", () -> new Item(new Item.Properties().food(DSProperties.Foods.PEPPER)));
    public static final RegistryObject<Item> SOUL_PEPPER = ITEMS.register("soul_pepper", () -> new Item(new Item.Properties().food(DSProperties.Foods.SOUL_PEPPER)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
