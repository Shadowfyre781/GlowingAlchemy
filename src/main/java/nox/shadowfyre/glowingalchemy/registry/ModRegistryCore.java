package nox.shadowfyre.glowingalchemy.registry;

/*
public class ModRegistryCore {
    public static void register(net.neoforged.bus.api.IEventBus modEventBus) {
        BlockRegistry.register(modEventBus);
        BlockFamilyRegistry.registerAll();
        modEventBus.addListener(ModRegistryCore::gatherClientData);
        modEventBus.addListener(ModRegistryCore::gatherServerData);
    }

    public static void gatherClientData(GatherDataEvent.Client event) {
        event.addProvider(
                new GroundcoverModelProvider(event.getGenerator().getPackOutput())
        );
        event.addProvider(new BlockFamilyModelProvider(event.getGenerator().getPackOutput()));
    }

    public static void gatherServerData(GatherDataEvent.Server event) {
        event.addProvider(
                new GMOakMetadataProvider(
                        event.getGenerator().getPackOutput(),
                        event.getLookupProvider()
                )
        );

        event.addProvider(
                new GroundcoverBlockTagProvider(event.getGenerator().getPackOutput())
        );
    }
}
*/