package net.azurune.dried_spice.datagen;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.block.AbstractDrinkBlock;
import net.azurune.dried_spice.block.PepperBlock;
import net.azurune.dried_spice.compat.ExcessiveBuildingCompat;
import net.azurune.dried_spice.register.DSBlocks;
import net.azurune.dried_spice.register.DSItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DSBlockLootTables extends BlockLootSubProvider {
    public DSBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.dropSelf(DSBlocks.COPPER_TEA_KETTLE.get());
        this.dropSelf(DSBlocks.IRON_TEA_KETTLE.get());
        this.dropSelf(DSBlocks.STOVE.get());
        this.dropSelf(ExcessiveBuildingCompat.SOUL_STOVE.get());
        this.addChancePlantTable(DSBlocks.AZALEA_FLOWER.get());
        LootItemCondition.Builder loottable$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(DSBlocks.PEPPER_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PepperBlock.AGE, 3));
        this.add(DSBlocks.PEPPER_CROP.get(), this.applyExplosionDecay(DSBlocks.PEPPER_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(DSItems.PEPPER_SEEDS.get()))).withPool(LootPool.lootPool().when(loottable$builder1).add(LootItem.lootTableItem(DSItems.PEPPER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 2))))));
        LootItemCondition.Builder loottable$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(DSBlocks.SOUL_PEPPER_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PepperBlock.AGE, 3));
        this.add(DSBlocks.SOUL_PEPPER_CROP.get(), this.applyExplosionDecay(DSBlocks.SOUL_PEPPER_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(DSItems.SOUL_PEPPER_SEEDS.get()))).withPool(LootPool.lootPool().when(loottable$builder2).add(LootItem.lootTableItem(DSItems.SOUL_PEPPER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 2))))));
        this.add(DSBlocks.DECORATIVE_BOTTLE.get(), this::createBottlesTable);
        this.add(DSBlocks.DECORATIVE_WATER_BOTTLE.get(), this::createBottlesTable);
        this.add(DSBlocks.DECORATIVE_DRAGONS_BREATH_BOTTLE.get(), this::createBottlesTable);
        this.add(DSBlocks.DECORATIVE_HONEY_BOTTLE.get(), this::createBottlesTable);
        this.add(DSBlocks.DECORATIVE_MILK_BOTTLE.get(), this::createBottlesTable);
        this.dropSelf(DSBlocks.COFFEE_BUSH.get());
        this.dropOther(DSBlocks.WILD_COFFEE_BUSH.get(), DSBlocks.COFFEE_BUSH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> DriedSpice.MODID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace())).collect(Collectors.toSet());
    }

    protected void addChancePlantTable(Block block) {
        LootTable.Builder loottable$builder = createSilkTouchOrShearsDispatchTable(block, LootItem.lootTableItem(block).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.33F, 0.55F, 0.77F, 1.0F)));
        this.add(block, loottable$builder);
    }

    protected LootTable.Builder createBottlesTable(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(2, 3, 4), (integer) -> {
            return SetItemCountFunction.setCount(ConstantValue.exactly((float) integer)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AbstractDrinkBlock.DECORATIVE_BOTTLES, integer)));
        }))));
    }
}
