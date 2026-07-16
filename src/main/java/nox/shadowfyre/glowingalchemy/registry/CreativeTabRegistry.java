package nox.shadowfyre.glowingalchemy.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;
import nox.shadowfyre.glowingalchemy.GlowingAlchemy;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Creative tabs for browsing everything BlockFamilyRegistry generated, without
 * needing /give. Split: one catch-all tab for the glowingalchemy namespace,
 * and five themed tabs for glowing_things (plus one "misc" tab for
 * glowing_things items -- glass/illumiglass/citrine -- that don't fit any of
 * the five requested categories).
 */
public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GlowingAlchemy.MODID);

    private static final Set<String> TREE_FAMILIES = Set.of("GlowOak", "RainbOak", "Maple", "Chestnut", "Evergreen", "Palm");
    private static final Set<String> STONE_FAMILIES = Set.of(
            "Colored Stone", "Glowing Stone", "Colored Stone Brick", "Glowing Stone Brick",
            "Colored Cobble", "Glowing Cobble", "Asphalt", "Glow Asphalt", "Concrete", "Glow Crete",
            "Plastic", "Glowplastic", "GlowIce", "GlowingPackedIce", "GlowingPermafrost"
    );
    private static final Set<String> SAND_FAMILIES = Set.of("Colored Sand", "Glowsand", "Colored Sandstone", "Glowing Sandstone");
    private static final Set<String> MUSHROOM_FAMILIES = Set.of("TinyGlowShrooms", "SmallGlowShroom", "GlowshroomCap");
    private static final Set<String> DIRT_FAMILIES = Set.of("Clay", "GlowClay", "ColoredMoss", "GlowingMoss", "Clover", "GlowingMycelium");
    // Anything in glowing_things not covered by the five sets above (glass, illumiglass, citrine, etc.)

    public static final Supplier<CreativeModeTab> GLOWING_ALCHEMY_TAB = TABS.register("glowingalchemy",
            () -> build("glowingalchemy", "dust", def -> def.namespace().equals("glowingalchemy")));

    public static final Supplier<CreativeModeTab> TREES_TAB = TABS.register("glowing_things_trees",
            () -> build("glowing_things_trees", "red_glowoak_leaves",
                    def -> def.namespace().equals("glowing_things") && TREE_FAMILIES.contains(def.family())));

    public static final Supplier<CreativeModeTab> STONE_TAB = TABS.register("glowing_things_stone",
            () -> build("glowing_things_stone", "red_stone",
                    def -> def.namespace().equals("glowing_things") && STONE_FAMILIES.contains(def.family())));

    public static final Supplier<CreativeModeTab> SAND_TAB = TABS.register("glowing_things_sand",
            () -> build("glowing_things_sand", "red_sand",
                    def -> def.namespace().equals("glowing_things") && SAND_FAMILIES.contains(def.family())));

    public static final Supplier<CreativeModeTab> MUSHROOMS_TAB = TABS.register("glowing_things_mushrooms",
            () -> build("glowing_things_mushrooms", "glow_tiny_glowshroom",
                    def -> def.namespace().equals("glowing_things") && MUSHROOM_FAMILIES.contains(def.family())));

    public static final Supplier<CreativeModeTab> DIRT_TAB = TABS.register("glowing_things_dirt",
            () -> build("glowing_things_dirt", "clay",
                    def -> def.namespace().equals("glowing_things") && DIRT_FAMILIES.contains(def.family())));

    public static final Supplier<CreativeModeTab> MISC_TAB = TABS.register("glowing_things_misc",
            () -> build("glowing_things_misc", "red_glass",
                    def -> def.namespace().equals("glowing_things")
                            && !TREE_FAMILIES.contains(def.family())
                            && !STONE_FAMILIES.contains(def.family())
                            && !SAND_FAMILIES.contains(def.family())
                            && !MUSHROOM_FAMILIES.contains(def.family())
                            && !DIRT_FAMILIES.contains(def.family())));

    private static CreativeModeTab build(String translationKey, String iconBlockId, java.util.function.Predicate<BlockDefinition> filter) {
        return CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GlowingAlchemy.MODID + "." + translationKey))
                .icon(() -> {
                    var block = BlockFamilyRegistry.REGISTERED_BLOCKS.get(iconBlockId);
                    return block != null ? new ItemStack(block.get()) : new ItemStack(Items.BARRIER);
                })
                .displayItems((params, output) -> {
                    for (var entry : BlockFamilyRegistry.REGISTERED_DEFS.entrySet()) {
                        if (filter.test(entry.getValue())) {
                            var block = BlockFamilyRegistry.REGISTERED_BLOCKS.get(entry.getKey());
                            if (block != null) {
                                output.accept(block.get());
                            }
                        }
                    }
                })
                .build();
    }
}