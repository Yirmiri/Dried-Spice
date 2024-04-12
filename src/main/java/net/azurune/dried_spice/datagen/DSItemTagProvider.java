package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DSItemTagProvider extends ItemTagsProvider {
    public static final TagKey<Item> DECORATIVE_BOTTLES = tag("decorative_bottles");
    public static final TagKey<Item> TEAS = tag("teas");

    public DSItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> aSuper, CompletableFuture<TagLookup<Block>> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, aSuper, future, DriedSpice.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DSItemTagProvider.DECORATIVE_BOTTLES).add(
                DSBlocks.DECORATIVE_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_WATER_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_HONEY_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_MILK_BOTTLE.get().asItem(),
                DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE.get().asItem()
        );

        this.tag(DSItemTagProvider.TEAS).add(
                DSItems.MUD_CUP_OF_AZALEA_TEA.get(),
                DSItems.MUD_CUP_OF_BLACK_TEA.get(),
                DSItems.MUD_CUP_OF_COFFEE.get(),
                DSItems.MUD_CUP_OF_CHOCOLATE_MILK.get(),
                DSItems.MUD_CUP_OF_DARK_COFFEE.get(),
                DSItems.MUD_CUP_OF_FUDGE_SUNDAE.get(),
                DSItems.MUD_CUP_OF_GREEN_TEA.get(),
                DSItems.MUD_CUP_OF_HONEY_TEA.get(),
                DSItems.MUD_CUP_OF_HOT_COCOA.get(),
                DSItems.MUD_CUP_OF_SPICY_HOT_COCOA.get(),
                DSItems.MUD_CUP_OF_SPICY_COFFEE.get(),
                DSItems.MUD_CUP_OF_KELP_TEA.get(),
                DSItems.MUD_CUP_OF_MANGO_TEA.get(),
                DSItems.MUD_CUP_OF_MUSHROOM_TEA.get(),
                DSItems.MUD_CUP_OF_ESPRESSO.get(),
                DSItems.MUD_CUP_OF_ICED_TEA.get()
        );
    }

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(new ResourceLocation(DriedSpice.MODID, name));
    }
}
