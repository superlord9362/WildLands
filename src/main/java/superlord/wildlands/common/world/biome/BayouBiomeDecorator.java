package superlord.wildlands.common.world.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import superlord.wildlands.init.WLPlacedFeatures;

public class BayouBiomeDecorator {

	@SuppressWarnings("unused")
	private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return biome(precipitation, temperature, downfall, spawnBuilder, biomeBuilder);
	}

	private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return (new Biome.BiomeBuilder())
				.hasPrecipitation(hasPrecipitation)
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(3031057)
						.waterFogColor(7172665)
						.fogColor(12638463)
						.skyColor(10865067)
						.foliageColorOverride(8242525)
						.grassColorOverride(8242525)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.mobSpawnSettings(spawnBuilder.build())
				.generationSettings(biomeBuilder.build())
				.build();
	}

	public static Biome decorateBayou(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder biomeFeatures = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
		BiomeDefaultFeatures.farmAnimals(spawnSettings);
		BiomeDefaultFeatures.commonSpawns(spawnSettings);
		spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
		spawnSettings.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.COD, 5, 2, 4));
		BiomeDefaultFeatures.addFossilDecoration(biomeFeatures);
		globalOverworldGeneration(biomeFeatures);
		BiomeDefaultFeatures.addDefaultOres(biomeFeatures);
		BiomeDefaultFeatures.addSwampClayDisk(biomeFeatures);
		addBayouVegetation(biomeFeatures);
		BiomeDefaultFeatures.addDefaultMushrooms(biomeFeatures);
		BiomeDefaultFeatures.addSwampExtraVegetation(biomeFeatures);
		biomeFeatures.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
		return biome(true, 0.7F, 0.9F, spawnSettings, biomeFeatures);
	}
	
	public static void addBayouVegetation(BiomeGenerationSettings.Builder p_126711_) {
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_BEARD_MOSS);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_CYPRESS_TREE);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_CATTAIL);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_DUCKWEED);
	      p_126711_.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_PALMETTO);
	   }

	private static void globalOverworldGeneration(BiomeGenerationSettings.Builder p_194870_) {
		BiomeDefaultFeatures.addDefaultCarversAndLakes(p_194870_);
		BiomeDefaultFeatures.addDefaultCrystalFormations(p_194870_);
		BiomeDefaultFeatures.addDefaultMonsterRoom(p_194870_);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(p_194870_);
		BiomeDefaultFeatures.addDefaultSprings(p_194870_);
		BiomeDefaultFeatures.addSurfaceFreezing(p_194870_);
	}


}
