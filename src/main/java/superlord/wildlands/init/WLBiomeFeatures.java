package superlord.wildlands.init;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;

public class WLBiomeFeatures {
	public static final ResourceKey<BiomeModifier> ADD_SWAMP_FEATURES = register("add_swamp_features");
	public static final ResourceKey<BiomeModifier> ADD_WARM_OCEAN_ROCK_FEATURES = register("add_warm_ocean_rock_features");
	public static final ResourceKey<BiomeModifier> ADD_WARM_OCEAN_VEGETAL_FEATURES = register("add_warm_ocean_vegetal_features");
	public static final ResourceKey<BiomeModifier> ADD_LUKEWARM_OCEAN_FEATURES = register("add_lukewarm_ocean_features");
	public static final ResourceKey<BiomeModifier> ADD_OCEAN_FEATURES = register("add_ocean_features");
	public static final ResourceKey<BiomeModifier> ADD_COLD_OCEAN_FEATURES = register("add_cold_ocean_features");
	public static final ResourceKey<BiomeModifier> ADD_FROZEN_OCEAN_FEATURES = register("add_frozen_ocean_features");
	public static final ResourceKey<BiomeModifier> ADD_BEACH_ROCK_FEATURES = register("add_beach_rock_features");
	public static final ResourceKey<BiomeModifier> ADD_BEACH_VEGETAL_FEATURES = register("add_beach_vegetal_features");
	public static final ResourceKey<BiomeModifier> ADD_STONY_SHORE_FEATURES = register("add_stony_shore_features");
	//public static final ResourceKey<BiomeModifier> ADD_FOREST_FEATURES = register("add_forest_features");
//	public static final ResourceKey<BiomeModifier> ADD_BAYOU_VEGETATION_FEATURES = register("add_bayou_vegetation_features");
	//public static final ResourceKey<BiomeModifier> ADD_BURNT_FOREST_FEATURES = register("add_burnt_forest_features");
	
	public static void bootstrap(BootstapContext<BiomeModifier> bootstapContext) {
		bootstapContext.register(ADD_SWAMP_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.SWAMP), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_MUD), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_WARM_OCEAN_ROCK_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_DOLERITE, WLPlacedFeatures.PLACED_DOLERITE_ROCK, WLPlacedFeatures.PLACED_DISK_GABBRO, WLPlacedFeatures.PLACED_GABBRO_ROCK, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK, WLPlacedFeatures.PLACED_DISK_FINE_SAND), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_WARM_OCEAN_VEGETAL_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_STARFISH, WLPlacedFeatures.PLACED_URCHIN), GenerationStep.Decoration.VEGETAL_DECORATION));
		bootstapContext.register(ADD_LUKEWARM_OCEAN_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_DOLERITE, WLPlacedFeatures.PLACED_DOLERITE_ROCK, WLPlacedFeatures.PLACED_DISK_GABBRO, WLPlacedFeatures.PLACED_GABBRO_ROCK, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK, WLPlacedFeatures.PLACED_DISK_FINE_SAND), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_OCEAN_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.OCEAN, Biomes.DEEP_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_DOLERITE, WLPlacedFeatures.PLACED_DOLERITE_ROCK, WLPlacedFeatures.PLACED_DISK_GABBRO, WLPlacedFeatures.PLACED_GABBRO_ROCK, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_COLD_OCEAN_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_DOLERITE, WLPlacedFeatures.PLACED_DOLERITE_ROCK, WLPlacedFeatures.PLACED_DISK_GABBRO, WLPlacedFeatures.PLACED_GABBRO_ROCK, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_FROZEN_OCEAN_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_DOLERITE, WLPlacedFeatures.PLACED_DOLERITE_ROCK, WLPlacedFeatures.PLACED_DISK_GABBRO, WLPlacedFeatures.PLACED_GABBRO_ROCK, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE), GenerationStep.Decoration.UNDERGROUND_ORES));
		bootstapContext.register(ADD_BEACH_ROCK_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.BEACH), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_DISK_FINE_SAND), GenerationStep.Decoration.UNDERGROUND_DECORATION));
		bootstapContext.register(ADD_BEACH_VEGETAL_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.BEACH), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_COCONUT_TREE), GenerationStep.Decoration.VEGETAL_DECORATION));
		bootstapContext.register(ADD_STONY_SHORE_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.STONY_SHORE), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE), GenerationStep.Decoration.UNDERGROUND_ORES));
		//bootstapContext.register(ADD_BAYOU_VEGETATION_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, WLBiomes.BAYOU), getPlacedFeature(bootstapContext, VegetationPlacements.FLOWER_SWAMP, VegetationPlacements.BROWN_MUSHROOM_NORMAL, VegetationPlacements.RED_MUSHROOM_NORMAL, VegetationPlacements.PATCH_SUGAR_CANE_SWAMP, VegetationPlacements.PATCH_PUMPKIN, AquaticPlacements.SEAGRASS_SWAMP, VegetationPlacements.PATCH_WATERLILY, VegetationPlacements.PATCH_GRASS_NORMAL, WLPlacedFeatures.PLACED_DUCKWEED, WLPlacedFeatures.PLACED_CATTAIL, WLPlacedFeatures.PLACED_PALMETTO, WLPlacedFeatures.PLACED_CYPRESS_TREE, WLPlacedFeatures.PLACED_BEARD_MOSS), GenerationStep.Decoration.VEGETAL_DECORATION));
		//bootstapContext.register(ADD_BURNT_FOREST_FEATURES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, WLBiomes.BURNT_FOREST), getPlacedFeature(bootstapContext, WLPlacedFeatures.PLACED_CHARRED_TREE, WLPlacedFeatures.PLACED_BURNT_GRASS, WLPlacedFeatures.PLACED_BURNT_BUSH), GenerationStep.Decoration.VEGETAL_DECORATION));
	}
	
	@NotNull
	private static HolderSet.Direct<Biome> getBiome(BootstapContext<BiomeModifier> bootstapContext, ResourceKey<Biome> biome) {
		return HolderSet.direct(bootstapContext.lookup(Registries.BIOME).getOrThrow(biome));
	}
	
	@SafeVarargs
	@NotNull
	private static HolderSet.Direct<Biome> getBiome(BootstapContext<BiomeModifier> bootstapContext, ResourceKey<Biome>... biome) {
		return HolderSet.direct(Stream.of(biome).map(resourceKey -> bootstapContext.lookup(Registries.BIOME).getOrThrow(resourceKey)).collect(Collectors.toList()));
	}
	
	@SafeVarargs
	@NotNull
	private static HolderSet.Direct<PlacedFeature> getPlacedFeature(BootstapContext<BiomeModifier> context, ResourceKey<PlacedFeature>... placedFeature) {
		return HolderSet.direct(Stream.of(placedFeature).map(resourceKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(resourceKey)).collect(Collectors.toList()));
	}
	
	@NotNull
	private static HolderSet.Direct<PlacedFeature> getPlacedFeature(BootstapContext<BiomeModifier> context, ResourceKey<PlacedFeature> placedFeature) {
		return HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeature));
	}
	
	@NotNull
	private static ResourceKey<BiomeModifier> register(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WildLands.MOD_ID, name));
	}
}
