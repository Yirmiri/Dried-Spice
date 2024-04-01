package net.azurune.dried_spice.register;

import net.azurune.dried_spice.block.PepperBlock;
import net.azurune.dried_spice.block.SoulPepperBlock;
import net.azurune.dried_spice.block.StoveBlock;
import net.azurune.dried_spice.block.TeaKettleBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.other.DSProperties;

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
