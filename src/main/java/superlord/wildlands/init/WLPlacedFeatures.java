package superlord.wildlands.init;

import java.util.List;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import superlord.wildlands.WildLands;

public class WLPlacedFeatures {

	public static void init() { }
	
	public static final ResourceKey<PlacedFeature> PLACED_STARFISH = registerPlacedFeature("placed_starfish");
	public static final ResourceKey<PlacedFeature> PLACED_URCHIN = registerPlacedFeature("placed_urchin");
	public static final ResourceKey<PlacedFeature> PLACED_DISK_MUD = registerPlacedFeature("placed_disk_mud");
	public static final ResourceKey<PlacedFeature> PLACED_DISK_FINE_SAND = registerPlacedFeature("placed_disk_fine_sand");
	public static final ResourceKey<PlacedFeature> PLACED_DISK_CONGLOMERATE = registerPlacedFeature("placed_disk_conglomerate");
	public static final ResourceKey<PlacedFeature> PLACED_DISK_GABBRO = registerPlacedFeature("placed_disk_gabbro");
	public static final ResourceKey<PlacedFeature> PLACED_DISK_DOLERITE = registerPlacedFeature("placed_disk_dolerite");
	public static final ResourceKey<PlacedFeature> PLACED_CONGLOMERATE_ROCK = registerPlacedFeature("placed_conglomerate_rock");
	public static final ResourceKey<PlacedFeature> PLACED_GABBRO_ROCK = registerPlacedFeature("placed_gabbro_rock");
	public static final ResourceKey<PlacedFeature> PLACED_DOLERITE_ROCK = registerPlacedFeature("placed_dolerite_rock");
	public static final ResourceKey<PlacedFeature> PLACED_OLIVINE_GABBRO_ROCK = registerPlacedFeature("placed_olivine_gabbro_rock");
	public static final ResourceKey<PlacedFeature> PLACED_CATTAIL = registerPlacedFeature("placed_cattail");
	public static final ResourceKey<PlacedFeature> PLACED_PALMETTO = registerPlacedFeature("placed_palmetto");
	public static final ResourceKey<PlacedFeature> PLACED_BURNT_GRASS = registerPlacedFeature("placed_burnt_grass");
	public static final ResourceKey<PlacedFeature> PLACED_BURNT_BUSH = registerPlacedFeature("placed_burnt_bush");
	public static final ResourceKey<PlacedFeature> PLACED_DUCKWEED = registerPlacedFeature("placed_duckweed");
	public static final ResourceKey<PlacedFeature> PLACED_BEARD_MOSS = registerPlacedFeature("placed_beard_moss");
	public static final ResourceKey<PlacedFeature> PLACED_CYPRESS_TREE = registerPlacedFeature("placed_cypress_tree");
	public static final ResourceKey<PlacedFeature> PLACED_COCONUT_TREE = registerPlacedFeature("placed_coconut_tree");
	public static final ResourceKey<PlacedFeature> PLACED_CHARRED_TREE = registerPlacedFeature("placed_charred_tree");
	
	public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
		HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(bootstapContext, PLACED_STARFISH, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_STARFISH), VegetationPlacements.worldSurfaceSquaredWithCount(4));
		PlacementUtils.register(bootstapContext, PLACED_URCHIN, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_URCHIN), VegetationPlacements.worldSurfaceSquaredWithCount(4));
		PlacementUtils.register(bootstapContext, PLACED_DISK_MUD, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DISK_MUD), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DISK_FINE_SAND, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DISK_FINE_SAND), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DISK_CONGLOMERATE, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DISK_CONGLOMERATE), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DISK_GABBRO, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DISK_GABBRO), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DISK_DOLERITE, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DISK_DOLERITE), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_CONGLOMERATE_ROCK, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_CONGLOMERATE_ROCK), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_GABBRO_ROCK, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_GABBRO_ROCK), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DOLERITE_ROCK, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DOLERITE_ROCK), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_OLIVINE_GABBRO_ROCK, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_OLIVINE_GABBRO_ROCK), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_CATTAIL, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_CATTAIL), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_PALMETTO, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_PALMETTO), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_BURNT_GRASS, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_BURNT_GRASS), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_BURNT_BUSH, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_BURNT_BUSH), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_DUCKWEED, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_DUCKWEED), VegetationPlacements.worldSurfaceSquaredWithCount(4));
		PlacementUtils.register(bootstapContext, PLACED_BEARD_MOSS, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_BEARD_MOSS), List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_CYPRESS_TREE, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_CYPRESS_TREE), List.of(PlacementUtils.countExtra(2,  0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
		PlacementUtils.register(bootstapContext, PLACED_COCONUT_TREE, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_COCONUT_TREE), List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR));
		PlacementUtils.register(bootstapContext, PLACED_CHARRED_TREE, holderGetter.getOrThrow(WLConfiguredFeatures.CONFIGURED_CHARRED_TREE), List.of(PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
	}
	
	public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(WildLands.MOD_ID, id));
	}
	
}
