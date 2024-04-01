package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.datagen.loot.DSLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = DriedSpice.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DSDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new DSRecipeProvider(packOutput));
        //generator.addProvider(event.includeClient(), new DSBlockStateProvider(packOutput, existingFileHelper));
        //generator.addProvider(event.includeClient(), new DSItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), DSLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new DSGlobalLootModifierProviders(packOutput));

        DSBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new DSBlockTagProvider(packOutput, lookupProvider, existingFileHelper));

        //generator.addProvider(event.includeServer(),
                //new DSItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
    }
}
