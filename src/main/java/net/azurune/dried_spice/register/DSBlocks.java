package net.azurune.dried_spice.register;

import net.azurune.dried_spice.block.*;
import net.azurune.dried_spice.block.drink.DecorativeBottleBlock;
import net.azurune.dried_spice.block.drink.DecorativeDragonsBreathBlock;
import net.azurune.dried_spice.block.drink.DecorativeDrinkBlock;
import net.azurune.dried_spice.block.drink.DecorativeThickDrinkBlock;
import net.azurune.dried_spice.block.entity.TeaKettleBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.uti.DSProperties;

import java.util.function.Supplier;

public class DSBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DriedSpice.MODID);

    //MISC
    public static final RegistryObject<Block> COPPER_TEA_KETTLE = register("copper_tea_kettle", () -> new TeaKettleBlock(DSProperties.BlockProperties.COPPER_TEA_KETTLE));
    public static final RegistryObject<Block> IRON_TEA_KETTLE = register("iron_tea_kettle", () -> new TeaKettleBlock(DSProperties.BlockProperties.IRON_TEA_KETTLE));
    public static final RegistryObject<Block> STOVE = register("stove", () -> new StoveBlock(1, DSProperties.BlockProperties.STOVE));

    //NATURAL
    public static final RegistryObject<Block> AZALEA_FLOWER = register("azalea_flower", () -> new GlowLichenBlock(DSProperties.BlockProperties.AZALEA_FLOWER));
    public static final RegistryObject<Block> PEPPER_CROP = register("pepper_crop", () -> new PepperBlock(DSProperties.BlockProperties.PEPPER_PLANT));
    public static final RegistryObject<Block> SOUL_PEPPER_CROP = register("soul_pepper_crop", () -> new SoulPepperBlock(DSProperties.BlockProperties.SOUL_PEPPER_PLANT));

    //DRINK BLOCKS
    public static final RegistryObject<Block> DECORATIVE_BOTTLE = register("decorative_bottle", () -> new DecorativeBottleBlock(DSProperties.BlockProperties.DECORATIVE_BOTTLE));
    public static final RegistryObject<Block> DECORATIVE_WATER_BOTTLE = register("decorative_water_bottle", () -> new DecorativeDrinkBlock(DSProperties.BlockProperties.DECORATIVE_BOTTLE));
    public static final RegistryObject<Block> DECORATIVE_HONEY_BOTTLE = register("decorative_honey_bottle", () -> new DecorativeThickDrinkBlock(DSProperties.BlockProperties.DECORATIVE_BOTTLE));
    public static final RegistryObject<Block> DECORATIVE_DRAGONS_BREATH_BOTTLE = register("decorative_dragons_breath_bottle", () -> new DecorativeDragonsBreathBlock(DSProperties.BlockProperties.GLOWING_DECORATIVE_BOTTLE));
    public static final RegistryObject<Block> DECORATIVE_MILK_BOTTLE = register("decorative_milk_bottle", () -> new DecorativeThickDrinkBlock(DSProperties.BlockProperties.DECORATIVE_BOTTLE));

    public static <B extends Block>RegistryObject<B> register(String name, Supplier<B> block) {
        RegistryObject<B> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <B extends Block> void registryBlockItem(String name, RegistryObject<B> block) {
        DSItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
