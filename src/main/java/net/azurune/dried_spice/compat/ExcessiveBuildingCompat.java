package net.azurune.dried_spice.compat;

import net.azurune.dried_spice.block.StoveBlock;
import net.azurune.dried_spice.item.drinks.teas.AncientTeaDrinkItem;
import net.azurune.dried_spice.util.DSProperties;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.excessive_building.potion.EBMobEffects;

public class ExcessiveBuildingCompat {
    //BLOCKS
    public static final RegistryObject<Block> SOUL_STOVE = DSBlocks.register("soul_stove", () -> new StoveBlock(2, DSProperties.BlockProperties.SOUL_STOVE));

    //ITEMS
    public static final RegistryObject<Item> ANCIENT_TEA_LEAVES = DSItems.ITEMS.register("ancient_tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.GARBAGE_FOOD_SOURCE)));
    public static final RegistryObject<Item> ANCIENT_DRIED_TEA_LEAVES = DSItems.ITEMS.register("ancient_dried_tea_leaves", () -> new Item(new Item.Properties().food(DSProperties.Foods.GARBAGE_FOOD_SOURCE)));

    //TEAS
    public static final RegistryObject<Item> MUD_CUP_OF_ANCIENT_TEA = DSItems.ITEMS.register("mud_cup_of_ancient_tea", () -> new AncientTeaDrinkItem(new Item.Properties().food(EBFoods.ANCIENT_TEA).stacksTo(8), 32));

    public class EBFoods {
        //COMPAT
        public static final FoodProperties ANCIENT_TEA = new FoodProperties.Builder().effect(new MobEffectInstance(EBMobEffects.REACHING.get(), 6000, 0), 1.0F).alwaysEat().build();
    }

    public static void register(IEventBus eventBus) {
    }
}