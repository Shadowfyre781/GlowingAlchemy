package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;
import static net.minecraft.core.registries.BuiltInRegistries.DATA_COMPONENT_TYPE;

public final class ModDataComponents {

    private ModDataComponents() {}

    public static final DeferredRegister.DataComponents COMPONENTS =
            DeferredRegister.createDataComponents(
                    Registries.DATA_COMPONENT_TYPE,
                    "glowingalchemy"
            );

    public static final DeferredHolder<
            DataComponentType<?>,
            DataComponentType<CustomData>
            > NOTEBOOK_DATA =
            COMPONENTS.registerComponentType(
                    "notebook_data",
                    builder -> builder.persistent(CustomData.CODEC)
            );

    public static void register(IEventBus modEventBus) {
        COMPONENTS.register(modEventBus);
    }
}