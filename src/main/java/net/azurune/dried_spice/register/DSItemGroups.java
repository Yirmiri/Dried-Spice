package net.azurune.dried_spice.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.azurune.dried_spice.DriedSpice;

public class DSItemGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DriedSpice.MODID);

    public static RegistryObject<CreativeModeTab> DRIED_SPICE_TAB = CREATIVE_MODE_TABS.register("z_dried_spice", () -> CreativeModeTab.builder().icon(() -> new ItemStack(DSBlocks.COPPER_TEA_KETTLE.get())).title(Component.translatable("dried_spice_tab")).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
