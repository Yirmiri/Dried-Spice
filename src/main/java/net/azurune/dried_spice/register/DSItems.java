package net.azurune.dried_spice.register;

import net.azurune.dried_spice.item.CupItem;
import net.azurune.dried_spice.item.GenericDrinkItem;
import net.azurune.dried_spice.item.drinks.*;
import net.azurune.dried_spice.util.DSProperties;
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
    public static final RegistryObject<Item> MUD_CUP = ITEMS.register("mud_cup", () -> new CupItem(new Item.Properties()));
    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans", () -> new Item(new Item.Properties()));

    //DRINKS
    public static final RegistryObject<Item> MUD_CUP_OF_WATER = ITEMS.register("mud_cup_of_water", () -> new GenericDrinkItem(new Item.Properties().food(DSProperties.Foods.WATER).stacksTo(1), 32));
    public static final RegistryObject<Item> MUD_CUP_OF_GREEN_TEA = ITEMS.register("mud_cup_of_green_tea", () -> new GreenTeaDrinkItem(new Item.Properties().food(DSProperties.Foods.GREEN_TEA).stacksTo(2), 32));
    public static final RegistryObject<Item> MUD_CUP_OF_COFFEE = ITEMS.register("mud_cup_of_coffee", () -> new CoffeeDrinkItem(new Item.Properties().food(DSProperties.Foods.COFFEE).stacksTo(4), 32));
    public static final RegistryObject<Item> MUD_CUP_OF_DARK_COFFEE = ITEMS.register("mud_cup_of_dark_coffee", () -> new DarkCoffeeDrinkItem(new Item.Properties().food(DSProperties.Foods.DARK_COFFEE).stacksTo(4), 32));
    public static final RegistryObject<Item> MUD_CUP_OF_MILK = ITEMS.register("mud_cup_of_milk", () -> new MilkDrinkItem(new Item.Properties().food(DSProperties.Foods.WATER).stacksTo(1), 32));
    public static final RegistryObject<Item> MUD_CUP_OF_MANGO_TEA = ITEMS.register("mud_cup_of_mango_tea", () -> new MangoTeaItem(new Item.Properties().food(DSProperties.Foods.WATER).stacksTo(1), 32));

    //NATURAL
    public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds", () -> new ItemNameBlockItem(DSBlocks.PEPPER_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> SOUL_PEPPER_SEEDS = ITEMS.register("soul_pepper_seeds", () -> new ItemNameBlockItem(DSBlocks.SOUL_PEPPER_CROP.get(), new Item.Properties()));

    //FOODS
    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", () -> new Item(new Item.Properties().food(DSProperties.Foods.PEPPER)));
    public static final RegistryObject<Item> SOUL_PEPPER = ITEMS.register("soul_pepper", () -> new Item(new Item.Properties().food(DSProperties.Foods.SOUL_PEPPER)));
    public static final RegistryObject<Item> MANGO = ITEMS.register("mango", () -> new Item(new Item.Properties().food(DSProperties.Foods.MANGO)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
