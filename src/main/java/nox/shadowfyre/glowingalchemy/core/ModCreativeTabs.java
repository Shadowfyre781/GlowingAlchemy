package nox.shadowfyre.glowingalchemy.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nox.shadowfyre.glowingalchemy.Registration;

public class ModCreativeTabs {
    // You MUST have the class declaration line above for the package statement to be valid

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "glowingalchemy");

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GLOWING_TAB = CREATIVE_TABS.register("glowing_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.glowingalchemy.glowing_things"))
                    .icon(() -> {
                        // Explicitly finding the white record to avoid any "cannot resolve" issues
                        return nox.shadowfyre.glowingalchemy.GlowPalette.COLORS.stream()
                                .filter(c -> "white".equals(c.name()))
                                .findFirst()
                                .map(color -> new ItemStack(nox.shadowfyre.glowingalchemy.Registration.GLOW_WOOLS.get(color).get()))
                                .orElseGet(() -> new ItemStack(net.minecraft.world.level.block.Blocks.WHITE_WOOL));
                    })
                    .displayItems((params, output) -> {
                        // This will be filled later via the Registration map
                    }).build());

    //\public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REDSTONE_TAB = CREATIVE_TABS.register("redstone_tab",
    //        () -> CreativeModeTab.builder()
    //                .title(Component.translatable("itemGroup.glowingalchemy.redstone_industry"))
    //                .icon(() -> new ItemStack(Registration.REDSTONE_MACHINE.get()))
    //                .displayItems((params, output) -> {
                        // This will be filled later
    //                }).build());
}