package superlord.wildlands.common.world.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BayouBiomeDecorator {

	private static int getSkyColorWithTemperatureModifier(float temperature) {
		float f = temperature / 3.0F;
		f = Mth.clamp(f, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.6325F - f * 0.1F, 0.44F + f * 0.11F, 1F);
	}

	@SuppressWarnings("unused")
	private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return biome(precipitation, temperature, downfall, spawnBuilder, biomeBuilder);
	}

	private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return (new Biome.BiomeBuilder())
				.precipitation(precipitation)
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(waterColor)
						.waterFogColor(waterFogColor)
						.fogColor(12638463)
						.skyColor(getSkyColorWithTemperatureModifier(temperature))
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.mobSpawnSettings(spawnBuilder.build())
				.generationSettings(biomeBuilder.build())
				.build();
	}

	public static Biome decorateBayou() {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder biomeFeatures = new BiomeGenerationSettings.Builder();
		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
		BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);
		mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
		BiomeDefaultFeatures.addFossilDecoration(biomegenerationsettings$builder);
		globalOverworldGeneration(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addSwampClayDisk(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addSwampVegetation(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addSwampExtraVegetation(biomegenerationsettings$builder);
		biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
		return biome(Biome.Precipitation.RAIN, 0.5F, 0.7F, 3031057, 7172665, spawnSettings, biomeFeatures);
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
