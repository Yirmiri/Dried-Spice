package net.azurune.dried_spice;

import net.azurune.dried_spice.compat.DSCompatRegistries;
import net.azurune.dried_spice.compat.ExcessiveBuildingCompat;
import net.azurune.dried_spice.datagen.loot.DSLootTableModifier;
import net.azurune.dried_spice.register.DSMobEffects;
import net.azurune.dried_spice.screen.TeaKettleScreen;
import net.azurune.dried_spice.util.DSStats;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.azurune.dried_spice.register.*;

import java.util.List;

@Mod(DriedSpice.MODID)
public class DriedSpice {
    public static final String MODID = "dried_spice";

    //Capes For devs, contributors, and supporters of Excessive Building
    public static final List<String> AZURUNE = List.of("Dev", "Yirmiri", "Nullmiri"); //Developers
    public static final List<String> COTTON_CANDY = List.of("Milkyfur"); //Artist
    public static final List<String> HEX = List.of("BackupCup"); //...one line caused so much chaos
    public static final List<String> SUPPORTERS = List.of(); //Supporters

    public DriedSpice() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        DSRecipeTypes.RECIPE_TYPES.register(modEventBus);
        DSMobEffects.MOB_EFFECTS.register(modEventBus);

        DSBlocks.register(modEventBus);
        DSItems.register(modEventBus);
        DSBlockEntities.register(modEventBus);
        DSItemGroups.register(modEventBus);
        DSMenuTypes.register(modEventBus);
        DSLootTableModifier.register(modEventBus);
        DSCompatRegistries.register(modEventBus);
        DSRecipeSerializers.register(modEventBus);

        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::commonStart);
    }

    public void commonStart(FMLCommonSetupEvent event) {
        event.enqueueWork(DSStats::init);
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.COPPER_TEA_KETTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.IRON_TEA_KETTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.AZALEA_FLOWER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.PEPPER_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.SOUL_PEPPER_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.DECORATIVE_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.DECORATIVE_WATER_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.DECORATIVE_HONEY_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DSBlocks.DECORATIVE_MILK_BOTTLE.get(), RenderType.cutout());


        MenuScreens.register(DSMenuTypes.TEA_KETTLE_MENU.get(), TeaKettleScreen::new);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent tab) {
        if (tab.getTab() == DSItemGroups.DRIED_SPICE_TAB.get()) {
            tab.accept(DSBlocks.STOVE);
            tab.accept(DSBlocks.COPPER_TEA_KETTLE);
            tab.accept(DSBlocks.AZALEA_FLOWER);
            tab.accept(DSBlocks.DECORATIVE_BOTTLE);
            tab.accept(DSBlocks.DECORATIVE_WATER_BOTTLE);
            tab.accept(DSBlocks.DECORATIVE_HONEY_BOTTLE);
            tab.accept(DSBlocks.DECORATIVE_MILK_BOTTLE);
            tab.accept(DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE);

            tab.accept(DSItems.MUD_CUP);
            tab.accept(DSItems.MUD_CUP_OF_WATER);
            tab.accept(DSItems.MUD_CUP_OF_MILK);
            tab.accept(DSItems.MUD_CUP_OF_GREEN_TEA);
            tab.accept(DSItems.MUD_CUP_OF_MANGO_TEA);
            tab.accept(DSItems.MUD_CUP_OF_COFFEE);
            tab.accept(DSItems.MUD_CUP_OF_DARK_COFFEE);

            tab.accept(DSItems.TEA_LEAVES);
            tab.accept(DSItems.DRIED_TEA_LEAVES);
            tab.accept(DSItems.DRIED_BROWN_MUSHROOM);
            tab.accept(DSItems.COFFEE_BEANS);
            tab.accept(DSItems.PEPPER_SEEDS);
            tab.accept(DSItems.SOUL_PEPPER_SEEDS);

            tab.accept(DSItems.MANGO);
            tab.accept(DSItems.PEPPER);
            tab.accept(DSItems.SOUL_PEPPER);
        }

        if (tab.getTab() == DSItemGroups.DRIED_SPICE_TAB.get() && (DSCompatRegistries.excessive_building)) {
            tab.accept(ExcessiveBuildingCompat.SOUL_STOVE);

            tab.accept(ExcessiveBuildingCompat.MUD_CUP_OF_ANCIENT_TEA);

            tab.accept(ExcessiveBuildingCompat.ANCIENT_TEA_LEAVES);
            tab.accept(ExcessiveBuildingCompat.ANCIENT_DRIED_TEA_LEAVES);
        }
    }
}
