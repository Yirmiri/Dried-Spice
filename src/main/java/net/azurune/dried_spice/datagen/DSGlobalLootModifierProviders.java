package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.compat.ExcessiveBuildingCompat;
import net.azurune.dried_spice.datagen.loot.AddItemModifier;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class DSGlobalLootModifierProviders extends GlobalLootModifierProvider {
    public DSGlobalLootModifierProviders(PackOutput output) {
        super(output, DriedSpice.MODID);
    }

    @Override
    protected void start() {
        add("pepper_from_bartering", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/piglin_bartering")).build(), LootItemRandomChanceCondition.randomChance(0.2f).build()
        }, DSItems.PEPPER.get(), 2, 4));

        add("tea_leaves_from_azalea", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("blocks/azalea_leaves")).build(), LootItemRandomChanceCondition.randomChance(0.2f).build()
        }, DSItems.TEA_LEAVES.get(), 1, 1));

        add("tea_leaves_from_flowering_azalea", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("blocks/flowering_azalea_leaves")).build(), LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, DSItems.TEA_LEAVES.get(), 1, 1));

        add("ancient_tea_leaves_from_ancient", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("excessive_building:blocks/ancient_leaves")).build(), LootItemRandomChanceCondition.randomChance(0.2f).build()
        }, ExcessiveBuildingCompat.ANCIENT_TEA_LEAVES.get(), 1, 1));

        add("azalea_flower_from_flowering_azalea", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("blocks/flowering_azalea_leaves")).build(), LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, DSBlocks.AZALEA_FLOWER.get().asItem(), 1, 1));
    }
}
