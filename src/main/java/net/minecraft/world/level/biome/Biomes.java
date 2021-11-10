package net.minecraft.world.level.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public abstract class Biomes
{
    public static final ResourceKey<Biome> OCEAN = register("ocean");
    public static final ResourceKey<Biome> PLAINS = register("plains");
    public static final ResourceKey<Biome> DESERT = register("desert");
    public static final ResourceKey<Biome> MOUNTAINS = register("mountains");
    public static final ResourceKey<Biome> FOREST = register("forest");
    public static final ResourceKey<Biome> TAIGA = register("taiga");
    public static final ResourceKey<Biome> SWAMP = register("swamp");
    public static final ResourceKey<Biome> RIVER = register("river");
    public static final ResourceKey<Biome> NETHER_WASTES = register("nether_wastes");
    public static final ResourceKey<Biome> THE_END = register("the_end");
    public static final ResourceKey<Biome> FROZEN_OCEAN = register("frozen_ocean");
    public static final ResourceKey<Biome> FROZEN_RIVER = register("frozen_river");
    public static final ResourceKey<Biome> SNOWY_TUNDRA = register("snowy_tundra");
    public static final ResourceKey<Biome> SNOWY_MOUNTAINS = register("snowy_mountains");
    public static final ResourceKey<Biome> MUSHROOM_FIELDS = register("mushroom_fields");
    public static final ResourceKey<Biome> MUSHROOM_FIELD_SHORE = register("mushroom_field_shore");
    public static final ResourceKey<Biome> BEACH = register("beach");
    public static final ResourceKey<Biome> DESERT_HILLS = register("desert_hills");
    public static final ResourceKey<Biome> WOODED_HILLS = register("wooded_hills");
    public static final ResourceKey<Biome> TAIGA_HILLS = register("taiga_hills");
    public static final ResourceKey<Biome> MOUNTAIN_EDGE = register("mountain_edge");
    public static final ResourceKey<Biome> JUNGLE = register("jungle");
    public static final ResourceKey<Biome> JUNGLE_HILLS = register("jungle_hills");
    public static final ResourceKey<Biome> JUNGLE_EDGE = register("jungle_edge");
    public static final ResourceKey<Biome> DEEP_OCEAN = register("deep_ocean");
    public static final ResourceKey<Biome> STONE_SHORE = register("stone_shore");
    public static final ResourceKey<Biome> SNOWY_BEACH = register("snowy_beach");
    public static final ResourceKey<Biome> BIRCH_FOREST = register("birch_forest");
    public static final ResourceKey<Biome> BIRCH_FOREST_HILLS = register("birch_forest_hills");
    public static final ResourceKey<Biome> DARK_FOREST = register("dark_forest");
    public static final ResourceKey<Biome> SNOWY_TAIGA = register("snowy_taiga");
    public static final ResourceKey<Biome> SNOWY_TAIGA_HILLS = register("snowy_taiga_hills");
    public static final ResourceKey<Biome> GIANT_TREE_TAIGA = register("giant_tree_taiga");
    public static final ResourceKey<Biome> GIANT_TREE_TAIGA_HILLS = register("giant_tree_taiga_hills");
    public static final ResourceKey<Biome> WOODED_MOUNTAINS = register("wooded_mountains");
    public static final ResourceKey<Biome> SAVANNA = register("savanna");
    public static final ResourceKey<Biome> SAVANNA_PLATEAU = register("savanna_plateau");
    public static final ResourceKey<Biome> BADLANDS = register("badlands");
    public static final ResourceKey<Biome> WOODED_BADLANDS_PLATEAU = register("wooded_badlands_plateau");
    public static final ResourceKey<Biome> BADLANDS_PLATEAU = register("badlands_plateau");
    public static final ResourceKey<Biome> SMALL_END_ISLANDS = register("small_end_islands");
    public static final ResourceKey<Biome> END_MIDLANDS = register("end_midlands");
    public static final ResourceKey<Biome> END_HIGHLANDS = register("end_highlands");
    public static final ResourceKey<Biome> END_BARRENS = register("end_barrens");
    public static final ResourceKey<Biome> WARM_OCEAN = register("warm_ocean");
    public static final ResourceKey<Biome> LUKEWARM_OCEAN = register("lukewarm_ocean");
    public static final ResourceKey<Biome> COLD_OCEAN = register("cold_ocean");
    public static final ResourceKey<Biome> DEEP_WARM_OCEAN = register("deep_warm_ocean");
    public static final ResourceKey<Biome> DEEP_LUKEWARM_OCEAN = register("deep_lukewarm_ocean");
    public static final ResourceKey<Biome> DEEP_COLD_OCEAN = register("deep_cold_ocean");
    public static final ResourceKey<Biome> DEEP_FROZEN_OCEAN = register("deep_frozen_ocean");
    public static final ResourceKey<Biome> THE_VOID = register("the_void");
    public static final ResourceKey<Biome> SUNFLOWER_PLAINS = register("sunflower_plains");
    public static final ResourceKey<Biome> DESERT_LAKES = register("desert_lakes");
    public static final ResourceKey<Biome> GRAVELLY_MOUNTAINS = register("gravelly_mountains");
    public static final ResourceKey<Biome> FLOWER_FOREST = register("flower_forest");
    public static final ResourceKey<Biome> TAIGA_MOUNTAINS = register("taiga_mountains");
    public static final ResourceKey<Biome> SWAMP_HILLS = register("swamp_hills");
    public static final ResourceKey<Biome> ICE_SPIKES = register("ice_spikes");
    public static final ResourceKey<Biome> MODIFIED_JUNGLE = register("modified_jungle");
    public static final ResourceKey<Biome> MODIFIED_JUNGLE_EDGE = register("modified_jungle_edge");
    public static final ResourceKey<Biome> TALL_BIRCH_FOREST = register("tall_birch_forest");
    public static final ResourceKey<Biome> TALL_BIRCH_HILLS = register("tall_birch_hills");
    public static final ResourceKey<Biome> DARK_FOREST_HILLS = register("dark_forest_hills");
    public static final ResourceKey<Biome> SNOWY_TAIGA_MOUNTAINS = register("snowy_taiga_mountains");
    public static final ResourceKey<Biome> GIANT_SPRUCE_TAIGA = register("giant_spruce_taiga");
    public static final ResourceKey<Biome> GIANT_SPRUCE_TAIGA_HILLS = register("giant_spruce_taiga_hills");
    public static final ResourceKey<Biome> MODIFIED_GRAVELLY_MOUNTAINS = register("modified_gravelly_mountains");
    public static final ResourceKey<Biome> SHATTERED_SAVANNA = register("shattered_savanna");
    public static final ResourceKey<Biome> SHATTERED_SAVANNA_PLATEAU = register("shattered_savanna_plateau");
    public static final ResourceKey<Biome> ERODED_BADLANDS = register("eroded_badlands");
    public static final ResourceKey<Biome> MODIFIED_WOODED_BADLANDS_PLATEAU = register("modified_wooded_badlands_plateau");
    public static final ResourceKey<Biome> MODIFIED_BADLANDS_PLATEAU = register("modified_badlands_plateau");
    public static final ResourceKey<Biome> BAMBOO_JUNGLE = register("bamboo_jungle");
    public static final ResourceKey<Biome> BAMBOO_JUNGLE_HILLS = register("bamboo_jungle_hills");
    public static final ResourceKey<Biome> SOUL_SAND_VALLEY = register("soul_sand_valley");
    public static final ResourceKey<Biome> CRIMSON_FOREST = register("crimson_forest");
    public static final ResourceKey<Biome> WARPED_FOREST = register("warped_forest");
    public static final ResourceKey<Biome> BASALT_DELTAS = register("basalt_deltas");
    public static final ResourceKey<Biome> DRIPSTONE_CAVES = register("dripstone_caves");
    public static final ResourceKey<Biome> LUSH_CAVES = register("lush_caves");

    private static ResourceKey<Biome> register(String pName)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(pName));
    }
}
