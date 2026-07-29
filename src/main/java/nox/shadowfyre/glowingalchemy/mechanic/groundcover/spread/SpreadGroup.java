package nox.shadowfyre.glowingalchemy.mechanic.groundcover.spread;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record SpreadGroup(
        TagKey<Block> sourceTag,
        TagKey<Block> replaceableTag
) {
}