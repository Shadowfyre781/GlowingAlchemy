package nox.shadowfyre.glowingalchemy;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import java.util.HashMap;
import java.util.Map;

public class Registration {

    // NAMESPACE 1: Glowing Things
    public static final DeferredRegister.Blocks GLOWING_BLOCKS = DeferredRegister.createBlocks("glowing_things");

    // NAMESPACE 2: Redstone Industry
    public static final DeferredRegister.Blocks INDUSTRY_BLOCKS = DeferredRegister.createBlocks("redstone_industry");

    // NAMESPACE 3: Elemental Alchemy (Main Fallback)
    public static final DeferredRegister.Blocks ALCHEMY_BLOCKS = DeferredRegister.createBlocks("glowing_alchemy");

    // Your wool container map
    public static final Map<GlowColor, DeferredHolder<Block, Block>> GLOW_WOOLS = new HashMap<>();

    static {
        for (GlowColor color : GlowPalette.FULL_16) {
            String registryName = "wool/" + color.subfolder() + "/" + color.name() + "_glow_wool";

            // NOTICE: We use GLOWING_BLOCKS here so these blocks belong to "glowing_things"
            DeferredHolder<Block, Block> blockHolder = GLOWING_BLOCKS.register(registryName, () -> new Block(
                    BlockBehaviour.Properties.of()
                            .destroyTime(0.8F)
                            .sound(SoundType.WOOL)
                            .lightLevel(state -> 10)
            ));

            GLOW_WOOLS.put(color, blockHolder);
        }
    }

    // This method hooks ALL THREE registries into your single mod bus at startup!
    public static void init(IEventBus modEventBus) {
        GLOWING_BLOCKS.register(modEventBus);
        INDUSTRY_BLOCKS.register(modEventBus);
        ALCHEMY_BLOCKS.register(modEventBus);
    }
}