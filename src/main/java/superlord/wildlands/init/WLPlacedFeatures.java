package superlord.wildlands.init;

import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

public class WLPlacedFeatures {

	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, WildLands.MOD_ID);

	public static final RegistryObject<PlacedFeature> PLACED_STARFISH = PLACED_FEATURES.register("placed_starfish", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_STARFISH.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(4)));
	public static final RegistryObject<PlacedFeature> PLACED_URCHIN = PLACED_FEATURES.register("placed_urchin", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_URCHIN.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(4)));

	public static final RegistryObject<PlacedFeature> PLACED_DISK_MUD = PLACED_FEATURES.register("placed_disk_mud", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DISK_MUD.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_DISK_FINE_SAND = PLACED_FEATURES.register("placed_disk_fine_sand", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DISK_FINE_SAND.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_DISK_CONGLOMERATE = PLACED_FEATURES.register("placed_disk_conglomerate", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DISK_CONGLOMERATE.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_DISK_GABBRO = PLACED_FEATURES.register("placed_disk_gabbro", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DISK_GABBRO.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_DISK_DOLERITE = PLACED_FEATURES.register("placed_disk_dolerite", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DISK_DOLERITE.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));

	public static final RegistryObject<PlacedFeature> PLACED_CONGLOMERATE_ROCK = PLACED_FEATURES.register("placed_conglomerate_rock", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_CONGLOMERATE_ROCK.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_GABBRO_ROCK = PLACED_FEATURES.register("placed_gabbro_rock", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_GABBRO_ROCK.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_DOLERITE_ROCK = PLACED_FEATURES.register("placed_dolerite_rock", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DOLERITE_ROCK.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_OLIVINE_GABBRO_ROCK = PLACED_FEATURES.register("placed_olivine_gabbro_rock", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow(), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));

	public static final RegistryObject<PlacedFeature> PLACED_CATTAIL = PLACED_FEATURES.register("placed_cattail", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_CATTAIL.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_PALMETTO = PLACED_FEATURES.register("placed_palmetto", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_PALMETTO.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_BURNT_GRASS = PLACED_FEATURES.register("placed_burnt_grass", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_BURNT_GRASS.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_BURNT_BUSH = PLACED_FEATURES.register("placed_burnt_bush", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_BURNT_BUSH.getHolder().orElseThrow(), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

	public static final RegistryObject<PlacedFeature> PLACED_DUCKWEED = PLACED_FEATURES.register("placed_duckweed", () -> new PlacedFeature(WLConfiguredFeatures.CONFIGURED_DUCKWEED.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(4)));

	public static final RegistryObject<PlacedFeature> PLACED_BEARD_MOSS = PLACED_FEATURES.register("placed_beard_moss", () -> new PlacedFeature(WLConfiguredFeatures.BEARD_MOSS.getHolder().orElseThrow(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
	
	public static final RegistryObject<PlacedFeature> PLACED_BAYOU_TREES = PLACED_FEATURES.register("placed_bayou_trees", () -> new PlacedFeature(WLConfiguredFeatures.CYPRESS_TREE.getHolder().orElseThrow(), List.of(PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));
	public static final RegistryObject<PlacedFeature> PLACED_COCONUT_TREES = PLACED_FEATURES.register("placed_coconut_trees", () -> new PlacedFeature(WLConfiguredFeatures.COCONUT_TREE.getHolder().orElseThrow(), List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)));
	public static final RegistryObject<PlacedFeature> PLACED_CHARRED_TREES = PLACED_FEATURES.register("placed_charred_trees", () -> new PlacedFeature(WLConfiguredFeatures.CHARRED_TREE.getHolder().orElseThrow(), List.of(PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

}
