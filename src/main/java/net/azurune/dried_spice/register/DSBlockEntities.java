package net.azurune.dried_spice.register;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.block.entity.TeaKettleBlockEntity;

public class DSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DriedSpice.MODID);

    public static final RegistryObject<BlockEntityType<TeaKettleBlockEntity>> TEA_KETTLE = BLOCK_ENTITIES.register("tea_kettle", () -> BlockEntityType.Builder.of(TeaKettleBlockEntity::new, DSBlocks.COPPER_TEA_KETTLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
