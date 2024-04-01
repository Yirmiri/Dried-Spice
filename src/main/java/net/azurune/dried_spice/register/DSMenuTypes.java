package net.azurune.dried_spice.register;

import net.azurune.dried_spice.DriedSpice;
import net.azurune.dried_spice.other.screen.TeaKettleMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DriedSpice.MODID);

    public static final RegistryObject<MenuType<TeaKettleMenu>> TEA_KETTLE_MENU = registerMenuType("tea_kettle_menu", TeaKettleMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
