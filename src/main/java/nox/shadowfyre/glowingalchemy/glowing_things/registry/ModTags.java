package nox.shadowfyre.glowingalchemy.glowing_things.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

public final class ModTags {
    private ModTags() {
    }

    public static final class Blocks {
        public static final TagKey<Block> IS_SIDE_WRAPPABLE =
                create("glowingalchemy", "is_side_wrappable");

        public static final TagKey<Block> SIDE_SPREADERS =
                create("glowingalchemy", "side_spreaders");

        public static final TagKey<Block> SPREAD_SOURCES_GLOWGRASS =
                create("glowingalchemy", "spread/sources/glowgrass");

        public static final TagKey<Block> SPREAD_REPLACEABLE_GLOWGRASS =
                create("glowingalchemy", "spread/replaceable/glowgrass");

        public static final TagKey<Block> SPREAD_SOURCES_GLOWMYCELIUM =
                create("glowingalchemy", "spread/sources/glowmycelium");

        public static final TagKey<Block> SPREAD_REPLACEABLE_GLOWMYCELIUM =
                create("glowingalchemy", "spread/replaceable/glowmycelium");

        private Blocks() {
        }

        private static TagKey<Block> create(String namespace, String path) {
            return TagKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(namespace, path)
            );
        }
    }
}